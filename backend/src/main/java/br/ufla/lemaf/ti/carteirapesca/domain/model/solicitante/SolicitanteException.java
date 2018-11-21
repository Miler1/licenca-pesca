package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;

/**
 * Exceção da agregação de Solicitante.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class SolicitanteException extends BaseException {

	/**
	 * Construtor da exceção de Solicitante.
	 *
	 * @param messageKey  Código da mensagem
	 * @param messageArgs Argumentos da mensagem.
	 */
	SolicitanteException(String messageKey, Object... messageArgs) {
		super(messageKey, messageArgs);
	}

}
