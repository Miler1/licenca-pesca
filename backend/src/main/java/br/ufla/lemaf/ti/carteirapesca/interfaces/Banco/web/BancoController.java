package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.web;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RemessaBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Controller
@Transactional
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private RemessaBuilderImpl remessaBuilder;

	@CrossOrigin("*")
	@GetMapping("/download-remessa")
	public ResponseEntity<InputStreamResource> geraRemessa() throws IOException {

		Remessa remessa = remessaBuilder.geraRemessa();

		if(remessa != null) {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.TEXT_PLAIN);

			InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(remessa.getArquivo().getCaminhoArquivo())));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		}

	}

	@PostMapping("/upload-retorno")
	public ResponseEntity<String> uploadArquivoRetorno(@RequestParam("file") MultipartFile multipartFile) throws IOException {

		File arquivoTemporario = new File(Objects.requireNonNull(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename()));

		Path pathArquivoRetorno = Paths.get(Properties.pathArquivoRetorno() + multipartFile.getOriginalFilename());
		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno.getParent());
		}

		FileUtils.copyFile(arquivoTemporario, arquivoRetorno, true);

		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

	}

}
