package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.RegistroServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ProtocoloDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

/**
 * Facade do serviço de Registro implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class RegistroServiceFacadeImpl implements RegistroServiceFacade {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProtocoloDTO novaLicenca(RegistroResource resource) {
		throw new NotImplementedException("Não implementado.");
	}
}
