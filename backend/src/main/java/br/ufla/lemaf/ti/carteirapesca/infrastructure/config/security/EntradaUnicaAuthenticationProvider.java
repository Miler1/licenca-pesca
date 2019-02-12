package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EntradaUnicaAuthenticationProvider implements AuthenticationProvider {

	@Value("${spring.security.entradaunica.portal-seguranca.url}")
	private String urlEntradaUnica;
//
//	@Autowired
//	private OAuthClientCadastroUnificado client;

	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@Override
	public Authentication authenticate(Authentication auth) {

		final AuthenticationToken tokenContainer = (AuthenticationToken) auth;
		final String token = tokenContainer.getToken();

		if (token == null) {
			throw new BadCredentialsException("Invalid token");
		}

		Map<String, String> args = new HashMap<>();
		args.put("sessionKeyEntradaUnica", token);

		Usuario usuario = WebServiceUtils.webServiceEU().executeRequestPost(this.urlEntradaUnica + "/external/usuario/buscaPorSessionKey"
			, args, Usuario.class);

//		Usuario usuario = WebServiceUtils.webServiceEU().buscaUsuarioSessionKey(token);

		if (usuario == null) {
			throw new BadCredentialsException("No user found for token - " + token);
		}

		Pessoa pessoa = this.pessoaFisicaService.findByUsuario(usuario);

		return new AuthenticationToken(token, usuario, pessoa);

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return AuthenticationToken.class.isAssignableFrom(authentication);
	}

}

