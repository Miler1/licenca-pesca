package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;

/**
 * Exceção ao Criar o Protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
class ProtocoloException extends BaseException {

	/**
	 * Construtor da Exceção do protocolo.
	 *
	 * @param messageKey O código da mensagem de erro
	 * @param messageArgs Os argumentos da mensagem de erro
	 */
	ProtocoloException(String messageKey, Object... messageArgs) {
		super(messageKey, messageArgs);
	}

}
