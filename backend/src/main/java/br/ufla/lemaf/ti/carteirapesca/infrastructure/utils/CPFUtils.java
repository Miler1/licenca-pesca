package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.com.caelum.stella.format.CPFFormatter;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.val;

/**
 * Facade de CPF.
 * <p>
 * Converte e valida os dados de CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class CPFUtils {

	/**
	 * Previne instanciação.
	 */
	private CPFUtils() {
	}

	/**
	 * Converte CPF no formato usado pela aplicação.
	 *
	 * @param cpf O CPF de acesso
	 * @return O CPF válidado e desformatado
	 */
	public static String unformat(final String cpf) {

		val formatter = new CPFFormatter();
		String novoCPF;

		if (Validate.isCpfValid(cpf)) {

			if (formatter.isFormatted(cpf)) {

				novoCPF = formatter.unformat(cpf);

			} else {

				novoCPF = cpf;

			}

			return novoCPF;

		} else {

			throw new ValidationException("acesso.resourceInvalid.cpfInvalido", cpf);

		}

	}

}
