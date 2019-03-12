package br.ufla.lemaf.ti.carteirapesca.interfaces.autenticacao.web;

import br.ufla.lemaf.ti.carteirapesca.domain.services.autenticacao.impl.AutenticacaoBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;

@Slf4j
@Controller
@Transactional
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

	@Autowired
	AutenticacaoBuilderImpl autenticacaoBuilder;

	@CrossOrigin("*")
	@GetMapping("/entrada-unica/{token}")
	public RedirectView autenticacaoEntradaUnica(@PathVariable("token") String token) {

		autenticacaoBuilder.autenticar(token);

		return new RedirectView(Properties.baseUrl() + "listagem-remessas");

	}

}
