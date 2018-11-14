package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo;

/**
 * Serviço de Registro.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface RegistroService {

	/**
	 * Cria uma nova Licença de pesca.
	 *
	 * @return O protocolo da Licença criada
	 */
	Protocolo criarNovaLicenca();

	/**
	 * Renova a licença.
	 *
	 * @param protocolo O protocolo da Licença
	 */
	void renovarLicenca(Protocolo protocolo);

}
