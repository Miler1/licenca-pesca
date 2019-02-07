package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoBuilder;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.TransacaoRetornoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class RetornoBuilderImpl implements RetornoBuilder {

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	RetornoRepository retornoRepository;

	@Override
	public Retorno salvaArquivo(File arquivoRetorno) {

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO.getCodigo());

		Arquivo arquivo = new Arquivo(arquivoRetorno.getPath(), arquivoRetorno.getPath(), tipoArquivo);
		Retorno retorno = new Retorno(arquivo);

		return retornoRepository.save(retorno);

	}

	@Override
	public void processarRetorno(Retorno retorno) throws IOException {

		Path caminho = Paths.get(retorno.getArquivo().getCaminhoArquivo());

		Stream<String> linhas = Files.lines(caminho);

		CabecalhoRetornoDTO cabecalho = new CabecalhoRetornoDTO(linhas.findFirst().toString());
		TraillerRetornoDTO trailler = new TraillerRetornoDTO(linhas.skip(linhas.count() - 1).findFirst().toString());

		linhas.skip(1).forEach(l -> {

			TransacaoRetornoDTO transacao = new TransacaoRetornoDTO(l);

		});

	}

}
