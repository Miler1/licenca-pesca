package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;

import java.io.File;
import java.io.IOException;

public interface RetornoBuilder {

	Retorno salvaArquivo(File arquivoRetorno);

	void processarRetorno(Retorno retorno) throws IOException;

}
