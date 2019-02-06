package br.ufla.lemaf.ti.carteirapesca.domain.services;

import java.io.File;
import java.io.IOException;

public interface RetornoBuilder {

	void salvaArquivo(File arquivoRetorno);

	void processarRetorno() throws IOException;

}
