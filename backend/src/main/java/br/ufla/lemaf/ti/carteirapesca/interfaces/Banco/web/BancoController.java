package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.web;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.ConvenioBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RemessaBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RetornoTituloBuilderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

@Slf4j
@Controller
@Transactional
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private RemessaBuilderImpl remessaBuilder;

	@Autowired
	private RetornoTituloBuilderImpl retornoBuilder;

	@Autowired
	private ConvenioBuilderImpl convenioBuilder;

	@CrossOrigin("*")
	@GetMapping("/gera-remessa")
	public ResponseEntity<InputStreamResource> geraRemessa() throws IOException {

		Remessa remessa = remessaBuilder.geraRemessa();

		if (remessa != null) {
			return preparaArquivoDownload(remessa.getArquivo());
		} else {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		}

	}

	@CrossOrigin("*")
	@GetMapping("/download-remessa/{idRemessa}")
	public ResponseEntity<InputStreamResource> downloadArquivoRemessa(@PathVariable("idRemessa") Integer idRemessa) throws FileNotFoundException {

		Arquivo arquivoRemessa = remessaBuilder.getArquivoRemessa(idRemessa);

		return preparaArquivoDownload(arquivoRemessa);

	}

	private ResponseEntity<InputStreamResource> preparaArquivoDownload(Arquivo arquivo) throws FileNotFoundException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType("application/octet-stream"));
		httpHeaders.setContentDispositionFormData("attachment", arquivo.getNome());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(arquivo.getCaminhoArquivo())));

		return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

	}

	@PostMapping("/upload-retorno")
	public ResponseEntity<String> uploadArquivoRetorno(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		Retorno retorno = retornoBuilder.salvaArquivo(multipartFile);

		retornoBuilder.processarRetorno(retorno);

		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/lista-remessa")
	public ResponseEntity<Page<Remessa>> listaRemessasPaginado(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		Page<Remessa> remessas = remessaBuilder.listaRemessas(pageable);

		return new ResponseEntity<>(remessas, HttpStatus.OK);

	}

}
