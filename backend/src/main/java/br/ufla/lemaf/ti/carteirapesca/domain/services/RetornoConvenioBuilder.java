package br.ufla.lemaf.ti.carteirapesca.domain.services;

import java.io.File;

public interface RetornoConvenioBuilder {

	void processaRetorno(File arquivoRetorno) throws Exception;

}
