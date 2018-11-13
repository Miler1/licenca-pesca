package br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Utilitario para validar a camada de interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class Validate {

	private static final Pattern EMAIL_REGEX = Pattern.compile(
		"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:"
			+ "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
		Pattern.CASE_INSENSITIVE
	);

	/**
	 * Confere se o objeto passado por parâmetro é nulo.
	 *
	 * @param object O objeto
	 * @return {@code true} se o objeto não for nulo
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * Confere se a string apresentada encaixa-se
	 * no padrão de email.
	 *
	 * @param email A string de email
	 * @return {@code true} se a string possuir um padrão
	 * email válido
	 */
	public static boolean isEmail(String email) {
		return EMAIL_REGEX.matcher(email).matches();
	}

	/**
	 * Checa se a data está no passado.
	 *
	 * @param date O data a se checar
	 * @return {@code true} se a data for anterior a hoje
	 */
	public static boolean isInPast(Date date) {
		return date.before(new Date());
	}

	/**
	 * Checa se o CPF é válido.
	 *
	 * @param cpf O cpf
	 * @return {@code true} se o CPF for válido
	 */
	public static boolean isCpfValid(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		try {
			org.apache.commons.lang3.Validate.notNull(cpf);
			cpfValidator.assertValid(cpf);
			return true;
		} catch (InvalidStateException | NullPointerException ex) {
			return false;
		}
	}

	/**
	 * Pra evitar implementação.
	 */
	private Validate() {
	}
}
