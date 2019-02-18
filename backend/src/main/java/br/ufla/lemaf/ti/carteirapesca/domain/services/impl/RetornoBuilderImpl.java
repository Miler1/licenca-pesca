package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.MotivoOcorrencia;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.TituloRetorno;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.MotivoOcorrenciaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoBuilder;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TransacaoRetornoDTO;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RetornoBuilderImpl implements RetornoBuilder {

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	RetornoRepository retornoRepository;

	@Autowired
	TituloRepository tituloRepository;

	@Autowired
	MotivoOcorrenciaRepository motivoOcorrenciaRepository;

	@Autowired
	TituloRetornoRepository tituloRetornoRepository;

	@Override
	public Retorno salvaArquivo(File arquivoRetorno) {

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO.getCodigo());

		Arquivo arquivo = new Arquivo(arquivoRetorno.getPath(), arquivoRetorno.getName(), tipoArquivo);
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

		Splitter.fixedLength(2)
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

		titulo.setRetornos(retornos);

		return titulo;

	}

}
