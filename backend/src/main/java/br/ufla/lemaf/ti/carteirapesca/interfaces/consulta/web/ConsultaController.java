package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.web;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Manipula as consultas da licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/api")
public class ConsultaController {

	private ConsultaServiceFacade facade;

	/**
	 * Injetando dependências.
	 *
	 * @param facade A Facede de consulta
	 */
	@Autowired
	public ConsultaController(ConsultaServiceFacade facade) {
		this.facade = facade;
	}

	/**
	 * Controller para a consulta de licença. Recebe como parâmetro
	 * o protocolo.
	 *
	 * @param protocolo O protocolo buscado
	 * @return {@link LicencaDTO} A Licença. Em forma de {@link ResponseEntity}
	 */
	@CrossOrigin("*")
	@GetMapping("/consultar")
	public ResponseEntity<LicencaDTO> consultar(@RequestParam final String protocolo) {

		var licenca = facade.consultar(protocolo);

		if (licenca == null)
			return new ResponseEntity<>(licenca, HttpStatus.NOT_FOUND);

		licenca.add(linkTo(methodOn(ConsultaController.class)
			.consultar(protocolo))
			.withSelfRel());

		return new ResponseEntity<>(licenca, HttpStatus.OK);

	}

	/**
	 * Download do boleto.
	 *
	 * @param protocolo O protocolo do Boleto
	 * @return O arquivo
	 */
	@CrossOrigin("*")
	@GetMapping("/boleto")
	public ResponseEntity<InputStreamResource> downloadBoleto(@RequestParam String protocolo) {

		try {

			var rotaDoBoleto = facade.buscarCaminho(protocolo, Constants.BOLETO);
			var boleto = new File(rotaDoBoleto);

			var httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_PDF);

			var isr = new InputStreamResource(new FileInputStream(boleto));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} catch (IOException e) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * Download da carteira de pesca.
	 *
	 * @param protocolo O protocolo da Carteira
	 * @return O arquivo
	 */
	@CrossOrigin("*")
	@GetMapping("/carteira")
	public ResponseEntity<InputStreamResource> downloadCarteira(@RequestParam String protocolo) {

		try {

			var rotaDaCarteira = facade.buscarCaminho(protocolo, Constants.CARTEIRA);
			var carteira = new File(rotaDaCarteira);

			var httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.IMAGE_PNG);

			var isr = new InputStreamResource(new FileInputStream(carteira));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} catch (IOException e) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}

	}

}
