package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

/**
 * Exceção de Validação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ValidationException extends BaseException {

	/**
	 * Construtor da exceção de validação.
	 *
	 * @param messageKey  O código da mensagem no
	 *                    arquivo de propriedades
	 * @param messageArgs Os argumentos da mensagem
	 */
	public ValidationException(String messageKey, Object... messageArgs) {
		super(messageKey, messageArgs);
	}

}
