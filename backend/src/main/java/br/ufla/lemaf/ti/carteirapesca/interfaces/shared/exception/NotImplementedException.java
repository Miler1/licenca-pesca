package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;

/**
 * Exceção lançada quando algum serviço ainda não foi desenvolvido
 * para a aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class NotImplementedException extends RuntimeException {

	/**
	 * Construtor da exceção de que o serviço ainda
	 * não foi implementado.
	 */
	public NotImplementedException() {
		super(Message.get("erro.notImplemented"));
	}

}
