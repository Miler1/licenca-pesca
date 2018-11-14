package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;

/**
 * Base para exceções.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class BaseException extends RuntimeException {

	private final String message;

	private final Object[] args;

	/**
	 * Construtor.
	 *
	 * @param messageKey A mensagem de erro
	 * @param args       Os argumentos da mensagem
	 */
	public BaseException(String messageKey, Object... args) {
		super(Message.get(messageKey, args));
		this.message = messageKey;
		this.args = args;
	}

	/**
	 * Busca a mensagem para o usuário da exceção.
	 *
	 * @return A Mensagem
	 */
	String getUserMessage() {
		return Message.get(message, args);
	}
}
