package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.web;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.ConvenioBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RemessaBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RetornoTituloBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.Controller.DefaultController;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
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

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
@Controller
@Transactional
@RequestMapping("/api") // ALTERAR PARA /banco
public class BancoController extends DefaultController {

	@Autowired
	private RemessaBuilderImpl remessaBuilder;

	@Autowired
	private RetornoTituloBuilderImpl retornoBuilder;

	@Autowired
	private RetornoRepository retornoRepository;

	@Autowired
	private ConvenioBuilderImpl convenioBuilder;

	@CrossOrigin("*")
	@GetMapping("/gera-remessa")
	public ResponseEntity<InputStreamResource> geraRemessa() throws IOException {

		Remessa remessa = remessaBuilder.geraRemessa();

		if (remessa != null) {
			return downloadArquivo(new File(remessa.getArquivo().getCaminhoArquivo()), remessa.getArquivo().getNome());
		} else {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		}

	}

	@CrossOrigin("*")
	@GetMapping("/download-remessa/{idRemessa}")
	public ResponseEntity<InputStreamResource> downloadArquivoRemessa(@PathVariable("idRemessa") Integer idRemessa) throws FileNotFoundException {

		Arquivo arquivoRemessa = remessaBuilder.getArquivoRemessa(idRemessa);

		return downloadArquivo(new File(arquivoRemessa.getCaminhoArquivo()), arquivoRemessa.getNome());

	}
	
	@CrossOrigin("*")
	@PostMapping("/upload-retorno")
	public ResponseEntity<String> uploadArquivoRetorno(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		Retorno retorno = retornoBuilder.salvaArquivo(multipartFile);

		retornoBuilder.processarRetorno(retorno);

		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

	}

	@CrossOrigin("*")
	@GetMapping("/lista-remessa")
	public ResponseEntity<Page<Remessa>> listaRemessasPaginado(@PageableDefault(size = 10) Pageable pageable) {

		Page<Remessa> remessas = remessaBuilder.listaRemessas(pageable);

		return new ResponseEntity<>(remessas, HttpStatus.OK);

	}

	@CrossOrigin("*")
	@GetMapping("/lista-retornos")
	public ResponseEntity<Page<Arquivo>> listaArquivosRetornoPaginado(@PageableDefault(size = 10) Pageable pageable) {

		Page<Arquivo> arquivosRetorno = retornoBuilder.listaRetornos(pageable);

		return new ResponseEntity<>(arquivosRetorno, HttpStatus.OK);

	}

}
