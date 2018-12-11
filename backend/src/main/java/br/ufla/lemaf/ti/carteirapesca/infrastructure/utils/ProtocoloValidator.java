package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.SimpleMessageProducer;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Verifica se uma cadeia (String) é válida para o Protocolo da Licença de Pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ProtocoloValidator implements Validator<String> {

	public static final Pattern FORMATED = Pattern.compile("(LP[ER])-([\\d]{4})/([\\d]{2})");
	public static final Pattern UNFORMATED = Pattern.compile("(LP[ER])([\\d]{4})([\\d]{2})");

	private static final Integer UNFORMATED_LENGTH = 9;

	private final boolean isFormatted;
	private final MessageProducer messageProducer;

	/**
	 * Construtor padrão de validador de Protocolo. Este considera, por padrão, que as
	 * cadeias não estão formatadas e utiliza um {@linkplain SimpleMessageProducer}
	 * para geração de mensagens.
	 */
	public ProtocoloValidator() {
		this(new SimpleMessageProducer(), false);
	}

	/**
	 * Construtor de validador de Protocolo. O validador utiliza um
	 * {@linkplain SimpleMessageProducer} para geração de mensagens. Leva em
	 * conta se o valor está ou não formatado.
	 *
	 * @param isFormatted Considera cadeia no formato de Protocolo:"LPX-9999-99" onde "X"
	 *                    pode ser E ou R, e "9" é um número.
	 */
	public ProtocoloValidator(boolean isFormatted) {
		this(new SimpleMessageProducer(), isFormatted);
	}

	/**
	 * <p>
	 * Construtor do Validador de Protocolo. Leva em consideração se o valor está
	 * formatado.
	 * </p>
	 * @param messageProducer          produtor de mensagem de erro.
	 * @param isFormatted              condição para considerar cadeia no formato de Protocolo:
	 *                                 "LPX-9999-99" onde "X" pode ser E ou R, e "9" é um número.
	 */
	public ProtocoloValidator(final MessageProducer messageProducer,
	                          final boolean isFormatted) {
		this.messageProducer = messageProducer;
		this.isFormatted = isFormatted;
	}

	/**
	 * Valida se a cadeia está de acordo com as regras de um Protocolo.
	 *
	 * @param protocolo O protocolo
	 * @return <code>true</code> se a cadeia é válida ou é nula;
	 * <code>false</code> caso contrario.
	 * @see br.com.caelum.stella.validation.Validator#assertValid(java.lang.Object)
	 */
	private List<ValidationMessage> getInvalidValues(String protocolo) {

		var errors = new ArrayList<ValidationMessage>();

		if (protocolo != null) {
			if (isFormatted && !FORMATED.matcher(protocolo).matches()) {
				errors.add(messageProducer.getMessage(ProtocoloError.INVALID_FORMAT));
			}

			String unformatedProtocolo = null;
			try {
				unformatedProtocolo = new ProtocoloFormatter().unformat(protocolo);
			} catch (IllegalArgumentException e) {
				errors.add(messageProducer.getMessage(ProtocoloError.INVALID_DIGITS));
				return errors;
			}

			if (unformatedProtocolo.length() != UNFORMATED_LENGTH
				|| !unformatedProtocolo.matches("(LP[ER])([\\d]{6})")) {
				errors.add(messageProducer.getMessage(ProtocoloError.INVALID_DIGITS));
			}
		}
		return errors;
	}

	@Override
	public boolean isEligible(String value) {
		if (value == null) {
			return false;
		}
		boolean result;
		if (isFormatted) {
			result = FORMATED.matcher(value).matches();
		} else {
			result = UNFORMATED.matcher(value).matches();
		}
		return result;
	}

	@Override
	public void assertValid(String cpf) {
		List<ValidationMessage> errors = getInvalidValues(cpf);
		if (!errors.isEmpty()) {
			throw new InvalidStateException(errors);
		}
	}

	@Override
	public List<ValidationMessage> invalidMessagesFor(String cpf) {
		return getInvalidValues(cpf);
	}

}
