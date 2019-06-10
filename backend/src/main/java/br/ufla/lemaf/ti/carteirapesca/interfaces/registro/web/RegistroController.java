package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.RegistroServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ProtocoloDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Manipula o registro de usuarios e a criação de
 * e atualização de licenças.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/api")
public class RegistroController {

	private RegistroServiceFacade registroServiceFacade;

	/**
	 * Injeta as dependências da controller de registro.
	 *
	 * @param registroServiceFacade A Facade de registro.
	 */
	@Autowired
	public RegistroController(final RegistroServiceFacade registroServiceFacade) {
		this.registroServiceFacade = registroServiceFacade;
	}

	/**
	 * Controller para o registrar uma nova licença. Recebe como parâmetro
	 * obrigatório os dados do usuário e os dados complemetares do cadastro.
	 * <p>
	 * Retornará o número do protocolo da licença.
	 *
	 * @param registroResource Paramêtro com o recurso de
	 *                         registro da Licença.
	 * @return {@link ProtocoloDTO} em forma
	 * de {@link ResponseEntity}
	 */
	@CrossOrigin("*")
	@PostMapping("/registrar")
	public ResponseEntity<ProtocoloDTO> registrar(
		@RequestBody final RegistroResource registroResource) throws Exception {

		WebServiceUtils.validarWebService();

		var protocoloLicenca = registroServiceFacade.registrar(registroResource);

		protocoloLicenca.add(linkTo(methodOn(RegistroController.class)
			.registrar(registroResource))
			.withSelfRel());

		return new ResponseEntity<>(protocoloLicenca, HttpStatus.ACCEPTED);

	}

	@CrossOrigin("*")
	@PostMapping("/renovar")
	public ResponseEntity<ProtocoloDTO> renovar(@RequestBody final RegistroResource registroResource) throws Exception {

		var protocoloLicenca = registroServiceFacade
			.renovarLicenca(registroResource);

		return new ResponseEntity<>(protocoloLicenca, HttpStatus.ACCEPTED);

	}

}
