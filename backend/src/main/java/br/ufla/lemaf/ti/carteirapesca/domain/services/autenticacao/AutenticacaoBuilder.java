package br.ufla.lemaf.ti.carteirapesca.domain.services.autenticacao;

import org.springframework.security.core.Authentication;

public interface AutenticacaoBuilder {

	Authentication autenticar(String token);

}
