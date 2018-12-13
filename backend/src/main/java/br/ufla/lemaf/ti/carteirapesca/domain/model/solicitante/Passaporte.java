package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;

/**
 * Value Object de Passaporte.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@Embeddable
@NoArgsConstructor
public final class Passaporte extends ValueObjectBase<Passaporte> {

	@Column(name = "nu_passaporte")
	private String numero;

	/**
	 * Construtor de passaporte.
	 *
	 * @param numero O número do passaporte
	 */
	public Passaporte(String numero) {
		try {

			Validate.notNull(numero, Message.get("solicitante.passaporte"));
			this.numero = numero;

		} catch (NullPointerException ex) {

			throw new SolicitanteException("solicitante.passaporte");

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return numero;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
