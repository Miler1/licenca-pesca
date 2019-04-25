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
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoArrecadacaoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoPagamentoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoConvenioBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ArquivoUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.TransacaoRetornoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RetornoConvenioBuilderImpl implements RetornoConvenioBuilder {

	private static final String EXTENSAO_ARQUIVO_RETORNO = ".RET";

	@Autowired
	ConvenioRepository convenioRepository;

	@Autowired
	TipoPagamentoRepository tipoPagamentoRepository;

	@Autowired
	TipoArrecadacaoRepository tipoArrecadacaoRepository;

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	RetornoConvenioRepository retornoRepository;

	@Autowired
	LicencaRepository licencaRepository;

	@Autowired
	ArquivoRepository arquivoRepository;

	public void buscaArquivoRetornoProcessamento() throws IOException {

		Path pathArquivoRetorno = Paths.get(Properties.pathArquivoRetornoDisponilizadoBanco());

		Files.newDirectoryStream(pathArquivoRetorno, path -> path.toString().endsWith(EXTENSAO_ARQUIVO_RETORNO))
			.forEach(p -> {

				try {

					File arquivoRetorno = new File(p.toString());

					processaRetorno(arquivoRetorno);

					FileUtils.forceDelete(arquivoRetorno);

				} catch (Exception e) {
					e.printStackTrace();
				}

			});

	}

	public void processaRetorno(File arquivoRetorno) throws Exception {

		Arquivo arquivo = salvaArquivo(arquivoRetorno);

		List<String> linhasArquivoRetorno = Files.lines(Paths.get(arquivo.getCaminhoArquivo()))
			.collect(Collectors.toList());

		new CabecalhoRetornoDTO().validaCabecalho(linhasArquivoRetorno.get(0));
		new TraillerRetornoDTO().validaTrailler(linhasArquivoRetorno.get(linhasArquivoRetorno.size() - 1));

		CabecalhoRetornoDTO cabecalho = new CabecalhoRetornoDTO(linhasArquivoRetorno.get(0));
		TraillerRetornoDTO trailler  = new TraillerRetornoDTO(linhasArquivoRetorno.get(linhasArquivoRetorno.size() - 1));

		RetornoConvenio retorno = new RetornoConvenio(cabecalho, trailler, arquivo);

		retorno = retornoRepository.save(retorno);

		processaRegistros(linhasArquivoRetorno, retorno);

	}

	private Arquivo salvaArquivo(File arquivoRetorno) throws Exception {

		File arquivoRetornoSalvo = salvaArquivoDiretorio(arquivoRetorno);

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO_ARRECADACAO.getCodigo());

		return new Arquivo(arquivoRetornoSalvo.getPath(), arquivoRetorno.getName(), tipoArquivo);

	}

	private void validaArquivo(MultipartFile multipartFile) throws Exception {

		if(!multipartFile.getOriginalFilename().endsWith(EXTENSAO_ARQUIVO_RETORNO)) {
			throw new Exception("A extensão do arquivo informado deve ser " + EXTENSAO_ARQUIVO_RETORNO);
		}

		if(arquivoRepository.findByNome(multipartFile.getOriginalFilename()) != null) {
			throw new Exception("O arquivo de retorno selecionado já foi processado");
		}
	}

	private File salvaArquivoDiretorio(File arquivoRetorno) throws Exception {

		final DateTimeFormatter FORMATO_DATA_MES_ANO = DateTimeFormatter.ofPattern("MM-YYYY");

		String pathSalvarArquivo = Properties.pathArquivoRetorno() +
			File.separator + "convenio" +
			File.separator + LocalDate.now().format(FORMATO_DATA_MES_ANO) +
			File.separator + UUID.randomUUID() +
			File.separator;

		return ArquivoUtils.salvaArquivoDiretorio(arquivoRetorno, pathSalvarArquivo);

	}

	private void processaRegistros(List<String> linhasArquivoRetorno, RetornoConvenio retorno) {

		List<TransacaoRetornoDTO> transacoes = getTransacoes(linhasArquivoRetorno);

		transacoes.forEach(t -> {

			Convenio convenio = convenioRepository.findByNossoNumeroAndCodigoBarras(t.getNumeroSequencialRegistro(), t.getCodigoBarras());

			Licenca licenca = licencaRepository.findByConvenio(convenio);

			if(convenio != null) {

				TipoArrecadacao tipoArrecadacao = tipoArrecadacaoRepository.findByCodigo(t.getFormaArrecadacao());

				PagamentoConvenio pagamento;

				if(t.getFormaParamento() == null) {
					pagamento = new PagamentoConvenio(t, tipoArrecadacao, null, retorno);
				} else {

					Optional<TipoPagamento> tipoPagamento = tipoPagamentoRepository.findById(t.getFormaParamento());
					pagamento = new PagamentoConvenio(t, tipoArrecadacao, tipoPagamento.get(), retorno);

				}

				licenca.getConvenio().setPagamento(pagamento);

				licenca.setDataVencimento(pagamento.getDataCredito());

				licencaRepository.save(licenca);

			}

		});

	}

	public List<TransacaoRetornoDTO> getTransacoes(final List<String> linhasArquivoRetorno) {

		linhasArquivoRetorno.remove(0);
		linhasArquivoRetorno.remove(linhasArquivoRetorno.size() - 1);

		List<TransacaoRetornoDTO> transacoes = new ArrayList<>();

		linhasArquivoRetorno.forEach(l -> {
			transacoes.add(new TransacaoRetornoDTO(l));
		});

		return transacoes;

	}
}
