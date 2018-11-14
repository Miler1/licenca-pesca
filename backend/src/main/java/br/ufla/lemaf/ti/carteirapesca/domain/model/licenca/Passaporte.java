package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

/**
 * Value Object de Passaporte.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public final class Passaporte extends ValueObjectBase<Passaporte> {

	private String numero;

	/**
	 * Construtor de passaporte.
	 *
	 * @param numero O número do passaporte
	 */
	public Passaporte(String numero) {
		Validate.notNull(numero, Message.get("passaporte.required"));

		this.numero = numero;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return numero;
	}

}
