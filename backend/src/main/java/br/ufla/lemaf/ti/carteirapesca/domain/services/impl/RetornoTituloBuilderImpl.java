package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.ArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.ConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.MotivoOcorrenciaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoTituloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ArquivoUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo.TransacaoRetornoDTO;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RetornoTituloBuilderImpl implements RetornoTituloBuilder {

	private static final String EXTENSAO_ARQUIVO_RETORNO = ".RET";

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	RetornoRepository retornoRepository;

	@Autowired
	TituloRepository tituloRepository;

	@Autowired
	MotivoOcorrenciaRepository motivoOcorrenciaRepository;

	@Autowired
	ArquivoRepository arquivoRepository;

	@Override
	public Retorno salvaArquivo(MultipartFile multipartFile) throws Exception {

		File arquivoRetorno = salvaArquivoDiretorio(multipartFile);

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO.getCodigo());

		Arquivo arquivo = new Arquivo(arquivoRetorno.getPath(), multipartFile.getOriginalFilename(), tipoArquivo);
		Retorno retorno = new Retorno(arquivo);

		return retornoRepository.save(retorno);

	}

	@Override
	public void processarRetorno(Retorno retorno) throws IOException {

		Path caminho = Paths.get(retorno.getArquivo().getCaminhoArquivo());

		List<String> linhasArquivoRetorno = Files.lines(caminho).collect(Collectors.toList());

		CabecalhoRetornoDTO cabecalho = new CabecalhoRetornoDTO(linhasArquivoRetorno.get(0));
		TraillerRetornoDTO trailler  = new TraillerRetornoDTO(linhasArquivoRetorno.get(linhasArquivoRetorno.size() - 1));

		retorno.atualizaRetorno(cabecalho, trailler);
		processaTitulos(linhasArquivoRetorno, retorno);

		retornoRepository.save(retorno);

	}

	public Arquivo getArquivoRetorno(Integer idRetorno) {

		Arquivo arquivo = arquivoRepository.findById(idRetorno).get();

		return arquivo;

	}

	public Page<Arquivo> listaRetornos(Pageable pageable) {

		return arquivoRepository.findByTipoArquivoOrderBy(tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO.getCodigo()), pageable);
	}

	private File salvaArquivoDiretorio(MultipartFile multipartFile) throws Exception {

		validaArquivo(multipartFile);

		final DateTimeFormatter FORMATO_DATA_MES_ANO = DateTimeFormatter.ofPattern("MM-YYYY");

		String pathSalvarArquivo = Properties.pathArquivoRetorno() +
			File.separator + "titulo" +
			File.separator + LocalDate.now().format(FORMATO_DATA_MES_ANO) +
			File.separator + UUID.randomUUID() + EXTENSAO_ARQUIVO_RETORNO;

		return ArquivoUtils.salvaArquivoDiretorio(multipartFile, pathSalvarArquivo);

	}

	private void validaArquivo(MultipartFile multipartFile) throws Exception {

		if(!multipartFile.getOriginalFilename().endsWith(EXTENSAO_ARQUIVO_RETORNO)) {
			throw new Exception("A extensão do arquivo informado deve ser " + EXTENSAO_ARQUIVO_RETORNO);
		}

		if(arquivoRepository.findByNome(multipartFile.getOriginalFilename()) != null) {
			throw new Exception("O arquivo de retorno selecionado já foi processado");
		}
	}

	private List<TransacaoRetornoDTO> getTransacoes(final List<String> linhasArquivoRetorno) {

		linhasArquivoRetorno.remove(0);
		linhasArquivoRetorno.remove(linhasArquivoRetorno.size() - 1);

		List<TransacaoRetornoDTO> transacoes = new ArrayList<>();

		linhasArquivoRetorno.forEach(l -> {
			transacoes.add(new TransacaoRetornoDTO(l));
		});

		return transacoes;

	}

	private void processaTitulos(List<String> linhasArquivoRetorno, Retorno retorno) {

		List<TransacaoRetornoDTO> transacoes = getTransacoes(linhasArquivoRetorno);

		transacoes.forEach(t -> {

			Titulo titulo = tituloRepository.findByNossoNumero(t.getNumeroDocumento().toString());

			if(titulo != null && t.getValorPago().compareTo(new BigDecimal(0)) == 1) {

				titulo.setValorPago(t.getValorPago());
				titulo.setDataPagamento(t.getDataCredito());

			}

			titulo = processaOcorrencia(titulo, retorno, t);

			tituloRepository.save(titulo);

		});

	}

	private Titulo processaOcorrencia(Titulo titulo,  Retorno retorno, TransacaoRetornoDTO transacao) {

		List<TituloRetorno> retornos = new ArrayList<>();

		Integer codigoOcorrencia = Integer.valueOf(transacao.getIdentificacaoOcorrencia());
		AtomicReference<Integer> codigoMotivoOcorrencia = new AtomicReference<>();

		final Integer N_POSICOES_REPRESENTAM_CODIGO = 2;

		Splitter.fixedLength(N_POSICOES_REPRESENTAM_CODIGO)
			.split(transacao.getMotivosRejeicao())
			.forEach(codigo -> {

				Integer codMotivoOcorrencia = Integer.valueOf(codigo);

				if(codigoMotivoOcorrencia.get() == null || (codigoMotivoOcorrencia.get() > 0 &&  codMotivoOcorrencia != 0)) {

					MotivoOcorrencia motivoOcorrencia = motivoOcorrenciaRepository.buscaPorOcorrenciaEMotivo(codigoOcorrencia, codMotivoOcorrencia);

					TituloRetorno tituloRetorno = new TituloRetorno(titulo, retorno, motivoOcorrencia);

					retornos.add(tituloRetorno);

				}

				codigoMotivoOcorrencia.set(Integer.valueOf(codigo));

			});

		final Integer OCORRENCIA_ENTRADA_REJEITADA = 3;

		if(codigoOcorrencia == OCORRENCIA_ENTRADA_REJEITADA) {
			titulo.setNecessitaGerarRemessa(true);
		}

		titulo.setRetornos(retornos);

		return titulo;

	}

}
