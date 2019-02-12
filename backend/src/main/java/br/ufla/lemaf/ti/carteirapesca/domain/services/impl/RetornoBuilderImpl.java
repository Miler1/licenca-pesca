package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoBuilder;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TransacaoRetornoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

		processaTitulos(linhasArquivoRetorno);

		retorno.atualizaRetorno(cabecalho, trailler);
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

	private void processaTitulos(List<String> linhasArquivoRetorno) {

		List<TransacaoRetornoDTO> transacoes = getTransacoes(linhasArquivoRetorno);

		transacoes.forEach(t -> {

			Titulo titulo = tituloRepository.findByNossoNumero(t.getNumeroDocumento());

			if(titulo != null && t.getValorPago().compareTo(new BigDecimal(0)) == 1) {

				titulo.setValorPago(t.getValorPago());
				titulo.setDataPagamento(t.getDataCredito());

				tituloRepository.save(titulo);

			}

			processaOcorrencia(titulo, t);

		});

	}

	private void processaOcorrencia(Titulo titulo, TransacaoRetornoDTO transacao) {



	}

}
