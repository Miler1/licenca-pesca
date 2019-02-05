package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoBuilder;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.CabecalhoRetornoDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RetornoBuilderImpl implements RetornoBuilder {

	@Override
	public void processarRetorno() throws IOException {

		Path caminho = Paths.get("/home/hiagosouza/git/amazonas/carteira-de-pesca/backend/arquivos/remessa/040219/CB040202.REM");

		Stream<String> linhas = Files.lines(caminho);

		CabecalhoRetornoDTO cabecalho = new CabecalhoRetornoDTO(linhas.findFirst().toString());

	}

}
