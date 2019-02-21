package br.ufla.lemaf.ti.carteirapesca.interfaces.autenticacao.web;

import br.ufla.lemaf.ti.carteirapesca.domain.services.autenticacao.impl.AutenticacaoBuilderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Controller
@Transactional
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

	@Autowired
	AutenticacaoBuilderImpl autenticacaoBuilder;

	@CrossOrigin("*")
	@GetMapping("/entrada-unica/{token}")
	public RedirectView autenticacaoEntradaUnica(@PathVariable("token") String token, HttpServletResponse response) throws IOException {

		Authentication autenticacao = autenticacaoBuilder.autenticar(token);

		return new RedirectView("/");

//		return new ResponseEntity<>(((AuthenticationToken) autenticacao).getUser(), HttpStatus.ACCEPTED);

	}

}
