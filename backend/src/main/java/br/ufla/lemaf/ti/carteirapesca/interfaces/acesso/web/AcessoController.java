package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;

/**
 * Manipula o acesso de usuarios.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/api")
public class AcessoController {

	private AcessoServiceFacade acessoServiceFacade;

	/**
	 * Injeta a dependencia da controller.
	 *
	 * @param acessoServiceFacade Serviço de Facade da camada
	 *                            de interface.
	 */
	@Autowired
	public AcessoController(final AcessoServiceFacade acessoServiceFacade) {
		this.acessoServiceFacade = acessoServiceFacade;
	}


	/**
	 * Controller para o acesso público. Recebe como parâmetro
	 * obrigatório o CPF do usuário.
	 * <p>
	 * Caso o mesmo tenha cadastro no entrada única, retorna seus dados,
	 * e se o mesmo não tiver cadastro, retornará {@link PessoaDTO} vazio.
	 *
	 * @param acessoResource Paramêtro com o recurso de
	 *                       acesso do Usuário.
	 * @param method         O método HTTP usado no web request.
	 * @param request        O web request.
	 * @return {@link PessoaDTO} Pessoa vazia caso não exista a mesma na
	 * base de dados, ou a pessoa instanciada com
	 * seus dados caso exista. Em forma de {@link ResponseEntity}
	 */
	@PostMapping("/acessar")
	@ApiOperation(value = "Dado o valor de CPF, garante acesso à"
		+ " aplicação, seguindo para a tela de cadastro, caso o mesmo"
		+ " não seja cadastrado ou mostrar seus dados caso esse já"
		+ " tenha cadastro.", authorizations = {
		@Authorization(value = "BASIC_AUTH")
	})
	public ResponseEntity<PessoaDTO> acessar(@RequestBody final AcessoResource acessoResource,
	                                         @ApiParam(hidden = true) final HttpMethod method,
	                                         final WebRequest request) {

		logInfo(method, request);

		// TODO - HATEOAS

		return new ResponseEntity<>(
			acessoServiceFacade.acessar(acessoResource),
			HttpStatus.ACCEPTED
		);

	}

	/**
	 * Log das chamadas da HTTP.
	 *
	 * @param method  O método HTTP usado no web request.
	 * @param request O web request.
	 */
	private static void logInfo(final HttpMethod method, final WebRequest request) {

		log.info("%s %s\n", method, request);

	}

}
