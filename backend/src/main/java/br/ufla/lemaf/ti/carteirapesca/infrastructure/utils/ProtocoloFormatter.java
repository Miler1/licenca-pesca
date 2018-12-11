package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.com.caelum.stella.format.BaseFormatter;
import br.com.caelum.stella.format.Formatter;

/**
 * Formata o protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ProtocoloFormatter implements Formatter {

	private final BaseFormatter base;

	/**
	 * Construtor do formatador de protocolo.
	 */
	public ProtocoloFormatter() {
		this.base = new BaseFormatter(
			ProtocoloValidator.FORMATED,
			"$1-$2/$3",
			ProtocoloValidator.UNFORMATED,
			"$1$2$3");
	}

	@Override
	public String format(String value) {
		return base.format(value);
	}

	@Override
	public String unformat(String value) {
		return base.unformat(value);
	}

	@Override
	public boolean isFormatted(String value) {
		return base.isFormatted(value);
	}

	@Override
	public boolean canBeFormatted(String value) {
		return base.canBeFormatted(value);
	}

}
