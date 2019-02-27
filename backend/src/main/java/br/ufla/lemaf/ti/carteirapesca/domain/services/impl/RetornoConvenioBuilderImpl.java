package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class RetornoConvenioBuilderImpl implements RetornoBuilder {

	@Override
	public Retorno salvaArquivo(MultipartFile multipartFile) throws Exception {
		return null;
	}

	@Override
	public void processarRetorno(Retorno retorno) throws IOException {

	}
}
