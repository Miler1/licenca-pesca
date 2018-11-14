package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.utils.Entity;
import lombok.NoArgsConstructor;

/**
 * Value Object de Solicitante, ou seja, a pessoa que solicitou
 * {@link Licenca}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@NoArgsConstructor
public class Solicitante implements Entity<Solicitante, SolicitanteId> {

	private SolicitanteId identity;

	/**
	 * Construtor de solicitante.
	 *
	 * @param identity A identificação do solicitante
	 */
	public Solicitante(final SolicitanteId identity) {
		this.identity = identity;
	}

	/**
	 * Busca uma String com um unico
	 * identificador do solicitante.
	 *
	 * @return O identificador em string
	 */
	public String buscarIdentificadorUnico() {
		return identity.buscarIdentificadorValido();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameIdentityAs(Solicitante other) {
		return other != null && identity.sameValueAs(other.identity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SolicitanteId identity() {
		return identity;
	}

	// Surrugate key para o Hibernate
	@SuppressWarnings("unused")
	private Long id;
}
