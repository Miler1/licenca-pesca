package br.ufla.lemaf.ti.carteirapesca.domain.shared;

/**
 * Utilitário para classes de Domain.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class DomainObjectUtils {

	/**
	 * Dado um valor atual, checa se o mesmo é nulo, em caso negativo
	 * o devolve, em caso positivo retorna o valor {@code safe}.
	 *
	 * @param actual O valor atual.
	 * @param safe   Um valor null-safe.
	 * @param <T>    O tipo.
	 * @return O valor atual, se ele não for nulo, ou
	 * o null-safe valor (fallback) se o valor atual for nulo.
	 */
	public static <T> T nullSafe(final T actual, final T safe) {
		return actual == null ? safe : actual;
	}

	/**
	 * Previne instanciação.
	 */
	private DomainObjectUtils() {
	}

}
