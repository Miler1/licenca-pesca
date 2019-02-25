package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RetornoTituloBuilder {

	Retorno salvaArquivo(MultipartFile multipartFile) throws Exception;

	void processarRetorno(Retorno retorno) throws IOException;

}
