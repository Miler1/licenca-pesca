package br.ufla.lemaf.ti.carteirapesca.domain.model;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

/**
 * Value Object do Protocolo da Licensça de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
public final class Protocolo implements ValueObject<Protocolo> {

	private String numero;

	private static final Pattern VALID_PATTERN = Pattern.compile(
		"[a-zA-Z]{3}[\\d]{5}\\[\\d]{4}"
	);

	/**
	 * Construtor.
	 *
	 * @param numero O número do protocolo.
	 */
	public Protocolo(String numero) {
		Validate.notNull(numero);
		Validate.isTrue(VALID_PATTERN.matcher(numero).matches(), "Protocolo Inválido.");
		this.numero = numero.toUpperCase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(Protocolo other) {
		throw new NotImplementedException("Não implementado.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo copy() {
		throw new NotImplementedException("Não implementado.");
	}
}
