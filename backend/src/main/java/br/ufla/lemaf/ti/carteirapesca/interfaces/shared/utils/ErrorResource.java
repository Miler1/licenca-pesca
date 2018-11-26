package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Recurso para respostas HTTP de erros.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
public class ErrorResource {

	private int status;

	private String message;

	private String error;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;

	private String exception;

	/**
	 * Construtor do recurso de Erro.
	 *
	 * @param status    O status do erro
	 * @param message   A mensagem do erro
	 * @param exception A exceção que ocorreu
	 */
	public ErrorResource(HttpStatus status, String message, Exception exception) {
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
		this.date = new Date();
		this.exception = exception.getClass().getName();
	}

	/**
	 * Gets date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return (Date) date.clone();
	}
}
