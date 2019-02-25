package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.Controller;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DefaultController {

	protected ResponseEntity<InputStreamResource> downloadArquivo(File file, String nomeArquivoComExtensao) throws FileNotFoundException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType("application/octet-stream"));
		httpHeaders.setContentDispositionFormData("attachment", nomeArquivoComExtensao);

		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));

		return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

	}

}
