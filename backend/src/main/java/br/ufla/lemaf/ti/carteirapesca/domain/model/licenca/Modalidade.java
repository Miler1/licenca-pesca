package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;

/**
 * Tipo de modalidade da Licença de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum Modalidade implements ValueObject<Modalidade> {
	ESPORTIVA, RECREATIVA, UNKNOWN;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(Modalidade other) {
		return this.equals(other);
	}
}
