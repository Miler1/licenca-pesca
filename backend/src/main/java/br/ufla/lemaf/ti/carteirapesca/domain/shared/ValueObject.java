package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Interface para Value Objects.
 * <p>
 * Um Value Object não possui um identificador e é imutável.
 *
 * @param <T> O Value Object.
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValueObject<T> {

	/**
	 * Value Objects são comparados por seus atributos,
	 * eles não possuem um identificador.
	 *
	 * @param other O outro Value Object.
	 * @return {@code true} se os atributosdo Value Object
	 * comparado e este forem iguais.
	 */
	boolean sameValueAs(T other);

	/**
	 * Value objects podem ser copiados livrementes.
	 *
	 * @return Uma cópia deste Value Object.
	 */
	T copy();

}
