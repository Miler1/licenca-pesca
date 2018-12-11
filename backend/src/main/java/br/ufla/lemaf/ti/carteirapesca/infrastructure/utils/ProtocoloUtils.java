package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.val;

/**
 * Facade de Protocolo.
 * <p>
 * Converte e valida os dados de Protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class ProtocoloUtils {

	/**
	 * Previne instanciação.
	 */
	private ProtocoloUtils() {
	}

	/**
	 * Converte CPF no formato usado pela aplicação.
	 *
	 * @param protocolo O Protocolo
	 * @return O Protocolo válidado e desformatado
	 */
	public static String unformat(final String protocolo) {

		val formatter = new ProtocoloFormatter();
		String novoProtocolo;

		if (Validate.isProtocoloValid(protocolo)) {

			if (formatter.isFormatted(protocolo)) {

				novoProtocolo = formatter.unformat(protocolo);

			} else {

				novoProtocolo = protocolo;

			}

			return novoProtocolo;

		} else {

			throw new ValidationException("consulta.resourceInvalid.protocoloInvalido", protocolo);

		}

	}

	/**
	 * Formatação de Protocolo.
	 *
	 * @param protocolo O Protocolo
	 * @return O Protocolo formatado
	 */
	public static String format(final String protocolo) {

		val formatter = new ProtocoloFormatter();
		String protocoloFormatado;

		if (Validate.isCpfValid(protocolo)) {

			if (formatter.isFormatted(protocolo)) {

				protocoloFormatado = protocolo;

			} else {

				protocoloFormatado = formatter.format(protocolo);

			}

			return protocoloFormatado;

		} else {

			throw new ValidationException("consulta.resourceInvalid.protocoloInvalido", protocolo);

		}

	}


}
