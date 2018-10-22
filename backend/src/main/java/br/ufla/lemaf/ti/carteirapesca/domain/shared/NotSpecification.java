package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * NOT Specification.
 *
 * Usado para criar uma nova specification que é o inverso (NOT) da specification dada.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> spec1;

	/**
	 * Cria uma nova NOT specification baseada em outra specification.
	 *
	 * @param spec1 Specification intanciada para {@code not}.
	 */
	public NotSpecification(final Specification<T> spec1) {
		this.spec1 = spec1;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(final T t) {
		return !spec1.isSatisfiedBy(t);
	}
}