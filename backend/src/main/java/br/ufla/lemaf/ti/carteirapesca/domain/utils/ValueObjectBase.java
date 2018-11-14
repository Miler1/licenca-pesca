package br.ufla.lemaf.ti.carteirapesca.domain.utils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implementação base para o Value Object.
 *
 * @param <T> O Value Object
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class ValueObjectBase<T extends ValueObject> implements ValueObject<T> {

	private transient int cashHashCode = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(final T other) {
		return other != null
			&& EqualsBuilder.reflectionEquals(this, other, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		/*
		 * Usando uma variável local para garantir que o campo
		 * "cashHashCode" só seja lido uma vez. Assim, não importa
		 * se várias threads computarem o hash code e o sobrescrevem,
		 * mas é importante que nunca retorne 0, o que poderia acontecer
		 * com várias leituras do campo "cashHashCode".
		 */
		int h = cashHashCode;
		if (h == 0) {
			// Lazy initialization.
			// Value Objects são imútáveis, então hash code nunca muda
			h = HashCodeBuilder.reflectionHashCode(this, false);
			cashHashCode = h;
		}
		return h;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		return sameValueAs((T) obj);
	}
}
