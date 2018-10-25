package br.ufla.lemaf.ti.carteirapesca.domain.model;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.NotImplementedException;

import java.util.regex.Pattern;

/**
 * Value Object do Protocolo da Licensça de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
public final class ProtocoloLicenca implements ValueObject<ProtocoloLicenca> {

	private String numero;

	private static final Pattern VALID_PATTERN = Pattern.compile(
		"[a-zA-Z]{3}[\\d]{5}\\[\\d]{4}"
	);

	/**
	 * Construtor.
	 *
	 * @param numero O número do protocolo.
	 */
	public ProtocoloLicenca(String numero) {
		Validate.notNull(numero);
		Validate.isTrue(VALID_PATTERN.matcher(numero).matches(), "Protocolo Inválido.");
		this.numero = numero.toUpperCase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(ProtocoloLicenca other) {
		throw new NotImplementedException("Não implementado.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProtocoloLicenca copy() {
		throw new NotImplementedException("Não implementado.");
	}
}
