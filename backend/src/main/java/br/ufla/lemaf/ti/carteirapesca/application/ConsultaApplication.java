package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;

/**
 * Serviço de Consulta da camada de application.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ConsultaApplication {

	/**
	 * Busca uma licença.
	 *
	 * @param protocolo O numero do protocolo.
	 * @return A Licença
	 */
	Licenca consulta(Protocolo protocolo);

}
