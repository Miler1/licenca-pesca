package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.com.caelum.stella.validation.InvalidValue;

/**
 * Enum de erros de protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum ProtocoloError implements InvalidValue {
	INVALID_DIGITS, INVALID_FORMAT
}
