package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Interface para a entidade, como explicado no livro "Domain Driven Design".
 * <p>
 * Todas as entidades do projeto possuem um atributo de
 * indentidade do tipo {@link Long} e nome id.
 *
 * @param <T>  A Entidade.
 * @param <ID> O Identificador da entidade.
 * @author Highlander Paiva
 * @since 1.0
 */
public interface Entity<T, ID> {

	/**
	 * Entidades são comparadas por identificador, não por atributos.
	 *
	 * @param other A outra entidade.
	 * @return {@code true} se a outra entidade possui o mesmo
	 * identificador, independente dos atributos.
	 */
	boolean sameIdentityAs(T other);

	/**
	 * Entidades possuem identificador.
	 *
	 * @return O identificador da entidade.
	 */
	ID identity();

}
