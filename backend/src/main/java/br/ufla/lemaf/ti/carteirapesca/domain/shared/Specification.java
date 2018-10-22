package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Interface de Specification.
 * <p>
 * Use {@link AbstractSpecification} como base para criar Specifications, e
 * apenas o método {@link #isSatisfiedBy(Object)} precisa ser implementado.
 *
 * @param <T> A specification.
 * @author Highlander Paiva
 * @since 1.0
 */
public interface Specification<T> {

	/**
	 * Checa se {@code t} satisfaz a specification.
	 *
	 * @param t Objeto para testa.
	 * @return {@code true} se {@code t} satisfaz a specification.
	 */
	boolean isSatisfiedBy(T t);

	/**
	 * Cria uma nova specification que é a operação AND da
	 * specification de {@code this} e outra specification.
	 *
	 * @param specification Specification para AND a esta specification.
	 * @return Uma nova specification.
	 */
	Specification<T> and(Specification<T> specification);

	/**
	 * Cria uma nova specification que é a operação OR da
	 * specification de {@code this} e outra specification.
	 *
	 * @param specification Specification para OR a esta specification.
	 * @return Uma nova specification.
	 */
	Specification<T> or(Specification<T> specification);

	/**
	 * Cria uma nova specification que é a operação NOT da
	 * specification de {@code this} e outra specification.
	 *
	 * @param specification Specification para NOT a esta specification.
	 * @return Uma nova specification.
	 */
	Specification<T> not(Specification<T> specification);
}
