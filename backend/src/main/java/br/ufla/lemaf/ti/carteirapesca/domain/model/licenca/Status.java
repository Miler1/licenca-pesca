package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;

/**
 * Status da Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum Status implements ValueObject<Status> {
	AGUARDANDO, ATIVO, INVALIDADO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(Status other) {
		return this.equals(other);
	}
}
