package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção [404: Not Found].
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
}
