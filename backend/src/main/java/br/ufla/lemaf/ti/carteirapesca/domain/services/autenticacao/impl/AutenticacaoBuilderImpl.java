package br.ufla.lemaf.ti.carteirapesca.domain.services.autenticacao.impl;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security.AuthenticationToken;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security.PessoaFisicaService;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import lombok.extern.slf4j.Slf4j;
import br.ufla.lemaf.beans.pessoa.Pessoa;
import br.ufla.lemaf.beans.pessoa.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutenticacaoBuilderImpl implements AuthenticationProvider {

	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	public Authentication autenticar(String token) {

		if (token == null) {
			throw new BadCredentialsException("Invalid token");
		}

		Usuario usuario = WebServiceUtils.webServiceEU().searchBySessionKey(token);

		if (usuario == null) {
			throw new BadCredentialsException("No user found for token - " + token);
		}

		Pessoa pessoa = this.pessoaFisicaService.findByUsuario(usuario);

		return new AuthenticationToken(token, usuario, pessoa);

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}

