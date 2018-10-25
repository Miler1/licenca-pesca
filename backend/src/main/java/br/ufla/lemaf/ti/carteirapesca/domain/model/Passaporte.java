package br.ufla.lemaf.ti.carteirapesca.domain.model;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Value Object de Passaporte.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Passaporte implements ValueObject<Passaporte> {

	private String numero;

	/**
	 * Value Objects são comparados por seus atributos,
	 * eles não possuem um identificador.
	 *
	 * @param other O outro Value Object.
	 * @return {@code true} se os atributosdo Value Object
	 * comparado e este forem iguais.
	 */
	@Override
	public boolean sameValueAs(Passaporte other) {
		return false;
	}

	/**
	 * Value objects podem ser copiados livrementes.
	 *
	 * @return Uma cópia deste Value Object.
	 */
	@Override
	public Passaporte copy() {
		return null;
	}
}
