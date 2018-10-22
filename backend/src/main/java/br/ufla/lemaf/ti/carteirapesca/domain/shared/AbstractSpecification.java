package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Implementação abstrata base para compor {@link Specification} com
 * implementações padrões para {@code and}, {@code or} e {@code not}.
 *
 * @author Highlander Paiva
 * @since 0.1
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

	/**
	 * {@inheritDoc}
	 */
	public abstract boolean isSatisfiedBy(T t);

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<T>(specification);
	}
}