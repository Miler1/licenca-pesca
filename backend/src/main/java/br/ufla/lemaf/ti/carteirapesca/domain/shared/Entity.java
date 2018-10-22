package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Interface para a entidade, como explicado no livro "Domain Driven Design".
 * Todas as entidades do projeto possuem um atributo de indentidade do tipo {@link Long} e
 * nome id.
 *
 * @author Highlander Paiva
 * @since 0.1
 */
public interface Entity<T, ID> {

	/**
	 * Entidades são comparadas por identificador, não por atributos.
	 *
	 * @param other A outra entidade.
	 *
	 * @return {@code true} se a outra entidade possui o mesmo identificador,
	 *         independente dos atributos.
	 */
	boolean sameIdentityAs(T other);

	/**
	 * Entidades possuem identificador.
	 *
	 * @return O identificador da entidade.
	 */
	ID identity();

}
