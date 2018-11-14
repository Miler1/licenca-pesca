package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.LicencaException;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.ErrorResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manipula as exceções da aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Fallback das exceções.
	 * <p>
	 * Caso o erro não seja especificado, lançará um erro genérico.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Uma resposta HTTP com erro 500 e a mensagem do erro padrão
	 */
	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleConflit(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			Message.get("erro.default"),
			new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR,
			request
		);
	}

	/**
	 * Manipulador de BaseException.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Resposta HTTP com código 500 e a mensagem de erro do usuário
	 */
	@ExceptionHandler(value = {BaseException.class})
	protected ResponseEntity<Object> handleConflit(BaseException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			new ErrorResource(HttpStatus.INTERNAL_SERVER_ERROR, ex.getUserMessage(), ex),
			new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR,
			request
		);
	}

	/**
	 * Manipulador de ValidationException.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Resposta HTTP com código 422 e a mensagem de erro do usuário
	 */
	@ExceptionHandler(value = {ValidationException.class})
	protected ResponseEntity<Object> handleConflit(ValidationException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			new ErrorResource(HttpStatus.UNPROCESSABLE_ENTITY, ex.getUserMessage(), ex),
			new HttpHeaders(),
			HttpStatus.UNPROCESSABLE_ENTITY,
			request
		);
	}

	/**
	 * Manipulador de BadRequestException.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Resposta HTTP com código 400 e a mensagem de erro do usuário
	 */
	@ExceptionHandler(value = {BadRequestException.class})
	protected ResponseEntity<Object> handleConflict(BadRequestException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			new ErrorResource(HttpStatus.BAD_REQUEST, ex.getMessage(), ex),
			new HttpHeaders(),
			HttpStatus.BAD_REQUEST,
			request
		);

	}

	/**
	 * Manipulador de NotImplementedException.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Resposta HTTP com código 500 e a mensagem de erro do usuário
	 */
	@ExceptionHandler(value = {NotImplementedException.class})
	protected ResponseEntity<Object> handleConflict(NotImplementedException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			new ErrorResource(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex),
			new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR,
			request
		);

	}
	/**
	 * Manipulador de LicencaException.
	 *
	 * @param ex      A exceção
	 * @param request A requisição
	 * @return Resposta HTTP com código 500 e a mensagem de erro do usuário
	 */

	@ExceptionHandler(value = {LicencaException.class})
	protected ResponseEntity<Object> handleConflit(LicencaException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		return handleExceptionInternal(
			ex,
			new ErrorResource(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex),
			new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR,
			request
		);
	}
}
