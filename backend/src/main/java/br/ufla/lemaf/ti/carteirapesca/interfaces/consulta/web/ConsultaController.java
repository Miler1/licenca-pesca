package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.web;

import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

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
		licenca.add(linkTo(methodOn(ConsultaController.class)
			.consultar(protocolo))
			.withSelfRel());

		return new ResponseEntity<>(licenca, HttpStatus.OK);

	}

}
