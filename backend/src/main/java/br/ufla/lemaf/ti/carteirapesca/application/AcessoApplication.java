package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.stereotype.Service;

/**
 * Serviço de Acesso da camada de application.
 * <p>
 * Gerencia o acesso de usuários autenticados ou acesso publico.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public interface AcessoApplication {

	/**
	 * Identifica e valida o acesso de determinado usuário,
	 * identificando-o por seu CPF ou Passaporte.
	 *
	 * @param acessoResource O Recurso para acesso.
	 * @return {@link Pessoa}
	 */
	Pessoa identificar(AcessoResource acessoResource);

}
