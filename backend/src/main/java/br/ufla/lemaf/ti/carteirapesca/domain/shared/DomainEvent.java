package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Um domain event é algo que é único, mas não possui um lifecycle.
 * <p>
 * A identidade pode ser explícita, por exemplo, o número de sequência
 * de um pagamento ou pode ser derivada de vários aspectos do evento,
 * como onde, quando e o que aconteceu.
 *
 * @param <T> O evento de domínio.
 * @author Highlander Paiva
 * @since 1.0
 */
public interface DomainEvent<T> {

	/**
	 * @param other O outro evento de domíniio.
	 * @return {@code true} se o dado evento de domínio e
	 * este evento são considerados o mesmo evento.
	 */
	boolean sameEventAs(T other);

}
