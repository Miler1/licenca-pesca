package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Especificações do protocolo.
 * <p>
 * Importante para gerar o número de protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "protocolo_sequence", schema = Constants.SCHEMA_CARTEIRA_PESCA)
public final class Sequence implements ValueObject<Sequence> {

	private static final Integer MAX_SEQUENCE_LENGTH = 9999;

	@Column(name = "nom_sequence")
	private String nome;

	@Column(name = "val_sequence")
	private Integer valor;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "idt_modalidade")
	private Modalidade modalidade;

	@Column(name = "val_sequence_year")
	private int year;

	/**
	 * Incrementa a sequência.
	 */
	public void incremente() {
		this.valor++;
	}

	/**
	 * Reseta a sequência.
	 */
	public void reset() {
		this.valor = 0;

		this.year = DateUtils.getThisYear();
	}

	/**
	 * Valida se o número de sequência é válido.
	 */
	public void validate() {
		if (this.valor > MAX_SEQUENCE_LENGTH)
			throw new ProtocoloException("protocolo.sequence.max");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(Sequence other) {
		return this.equals(other);
	}

	// Surrogate key
	@Id
	private Integer id;

}
