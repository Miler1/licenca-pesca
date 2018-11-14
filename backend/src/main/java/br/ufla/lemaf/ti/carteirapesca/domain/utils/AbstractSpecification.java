package br.ufla.lemaf.ti.carteirapesca.domain.utils;

/**
 * Classe abstrata base para compor {@link Specification} com
 * implementações padrões para {@code and}, {@code or} e {@code not}.
 *
 * @param <T> A specification.
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<>(specification);
	}
}
