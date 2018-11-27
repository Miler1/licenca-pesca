package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;

/**
 * Value Object para identificação do Solicitante.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
final class SolicitanteId extends ValueObjectBase<SolicitanteId> {

	private final CPF cpf;

	private final Passaporte passaporte;

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
	String buscarIdentificadorValido() {
		if (Validate.isNull(cpf)) {
			return passaporte.toString();
		} else {
			return cpf.getNumero();
		}
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
