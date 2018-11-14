package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.utils.ValueObject;

/**
 * Status da Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum LicencaStatus implements ValueObject<LicencaStatus> {
	AGUARDANDO, ATIVO, INVALIDADO;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(LicencaStatus other) {
		return this.equals(other);
	}
}
