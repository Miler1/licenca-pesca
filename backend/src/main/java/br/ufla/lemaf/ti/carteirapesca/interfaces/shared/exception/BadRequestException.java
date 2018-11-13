package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de [400: Bad Request].
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	/**
	 * Construtor da exceção de BadRequest.
	 */
	public BadRequestException() {
		super(Message.get("erro.badRequest"));
	}

}
