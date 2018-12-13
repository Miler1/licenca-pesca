package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

/**
 * Exceção dos serviços do Entrada Unica.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class EntradaUnicaException extends BaseException {

	/**
	 * Construtor da exceção do Entrada Unica.
	 *
	 * @param messageKey  O código da mensagem no
	 *                    arquivo de propriedades
	 * @param messageArgs Os argumentos da mensagem
	 */
	public EntradaUnicaException(String messageKey, Object... messageArgs) {
		super(messageKey, messageArgs);
	}
}
