package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Value Object para identificação do Solicitante.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Embeddable
@NoArgsConstructor
public final class SolicitanteId extends ValueObjectBase<SolicitanteId> {

	@Embedded
	@AttributeOverride(name = "numero", column = @Column(name = "nu_cpf"))
	private CPF cpf;

	@Embedded
	@AttributeOverride(name = "numero", column = @Column(name = "nu_passaporte"))
	private Passaporte passaporte;

	/**
	 * Contrutor do SolicitanteId.
	 *
	 * @param cpf        O CPF do solicitante
	 * @param passaporte O passaporte do solicitante
	 */
	SolicitanteId(final CPF cpf, final Passaporte passaporte) {
		if (Validate.isNull(cpf) && Validate.isNull(passaporte))
			throw new SolicitanteException("solicitante.identity");

		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	/**
	 * Busca o CPF do solicitante.
	 *
	 * @return O CPF
	 */
	public CPF cpf() {
		return cpf;
	}

	/**
	 * Busca o Passaporte do Solicitante.
	 *
	 * @return O passaporte
	 */
	public Passaporte passaporte() {
		return passaporte;
	}

	/**
	 * Busca um Identificador válido do solicitante.
	 *
	 * @return String com o CPF ou Passaporte
	 */
	public String valor() {
		if (Validate.isNull(cpf)) {
			return passaporte.toString();
		} else {
			return cpf.getNumero();
		}
	}

	/**
	 * @return Se o identificador é um CPF
	 */
	public boolean isCPF() {
		return !Validate.isNull(cpf);
	}

	/**
	 * @return Se o identificador é um CPF
	 */
	public boolean isPassaporte() {
		return !Validate.isNull(passaporte);
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
