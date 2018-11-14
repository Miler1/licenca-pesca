package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;

/**
 * Exceção ao Criar a Licença de Pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class CannotCreateLicencaException extends BaseException {

	/**
	 * Construtor da exceção ao criar Licença.
	 *
	 * @param messageKey  Código da mensagem
	 * @param messageArgs Argumentos da mensagem.
	 */
	public CannotCreateLicencaException(String messageKey, Object... messageArgs) {
		super(messageKey, messageArgs);
	}

}
