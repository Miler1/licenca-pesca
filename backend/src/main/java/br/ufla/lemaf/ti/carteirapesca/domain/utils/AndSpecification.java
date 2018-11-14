package br.ufla.lemaf.ti.carteirapesca.domain.utils;

/**
 * AND specification.
 * <p>
 * Usada para criar uma nova specification que são AND de duas outras
 * specifications.
 *
 * @param <T> A specification.
 * @author Highlander Paiva
 * @since 1.0
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> spec1;
	private Specification<T> spec2;

	/**
	 * Cria uma nova AND specification baseado nas duas
	 * outras specifications.
	 *
	 * @param spec1 Specification um.
	 * @param spec2 Specification dois.
	 */
	public AndSpecification(final Specification<T> spec1,
	                        final Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(final T t) {
		return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
	}
}
