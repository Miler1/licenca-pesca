package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RegistroService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;

/**
 * Serviço de registro da licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class RegistroServiceImpl implements RegistroService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo criarNovaLicenca() {
		throw new NotImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renovarLicenca(Protocolo protocolo) {
		throw new NotImplementedException();
	}
}
