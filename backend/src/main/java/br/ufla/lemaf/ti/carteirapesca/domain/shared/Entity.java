package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Interface para a entidade.
 * <p>
 * Todas {@link Entity} possui um identificador e
 * são distiguidas pelo mesmo, ainda que suas propriedades
 * sejam as mesmas, se sue identificador for distinto
 * as duas Entidades são consideradas distintas.
 * <p>
 * Uma {@link Entity} não é imutável,
 * ou seja, pode ser modificada.
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
