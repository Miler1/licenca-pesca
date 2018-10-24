package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.UsuarioDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Manipula o acesso de usuarios.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/")
public class AcessoController {

	private AcessoApplication acessoApplication;

	/**
	 * @param request A requisição.
	 * @param binder  O Data binder.
	 * @throws Exception Exceção.
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	                          ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(
			Date.class,
			new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), false)
		);
	}

	/**
	 * Controler de acesso.
	 *
	 * @param request  A requisição.
	 * @param response A resposta.
	 * @param command  O comando de Acesso.
	 * @throws IOException Se a entrada não for entregue.
	 */
	@GetMapping("acessar")
	@ApiOperation(value = "Dado o valor de CPF, garante acesso à"
		+ " aplicação, seguindo para a tela de cadastro, caso o mesmo"
		+ " não seja cadastrado ou mostrar seus dados caso esse já"
		+ " tenha cadastro.", authorizations = {
		@Authorization(value = "BASIC_AUTH")
	})
	public void acessar(HttpServletRequest request,
	                    HttpServletResponse response,
	                    AcessoCommand command) throws IOException {

		UsuarioDTO usuario;

		if (acessoApplication.existeUsuario(command.getCpf()))
			usuario = acessoApplication.buscarUsuario(command.getCpf());


		// TODO
		throw new NotImplementedException("Não implementado ainda!");


	}

	/**
	 * Método Setter de {@link AcessoApplication}.
	 *
	 * @param acessoApplication O Serviço de AcessoApplication.
	 */
	public void setAcessoApplication(AcessoApplication acessoApplication) {
		this.acessoApplication = acessoApplication;
	}

}
