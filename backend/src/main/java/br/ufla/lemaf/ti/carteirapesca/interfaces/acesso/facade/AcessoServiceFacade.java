package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade;

import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTO;

/**
 * Serviço de Facade de Acesso da camada de interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface AcessoServiceFacade {

	/**
	 * Acessar.
	 *
	 * @param resource O recurso de acesso do usuário.
	 * @return A pessoa.
	 */
	PessoaDTO acessar(AcessoResource resource);

}
