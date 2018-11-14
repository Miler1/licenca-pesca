package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.utils.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

/**
 * Uma Licença de Pesca.
 * <p>
 * Classe central da Domain model, e raiz da agregação.
 * <p>
 * Uma licença tem como identificador único o {@link Protocolo},
 * possui um status de condição, {@link LicencaStatus}, e um
 * {@link Solicitante}.
 */
@NoArgsConstructor
public class Licenca implements Entity<Licenca, Protocolo> {

	private Protocolo protocolo;

	private Solicitante solicitante;

	/**
	 * Construtor da Licenca de pesca.
	 *
	 * @param protocolo   O número do protocolo
	 * @param solicitante O Solicitante da licença
	 */
	public Licenca(final Protocolo protocolo, final Solicitante solicitante) {
		Validate.notNull(protocolo, Message.get("licenca.protocoloRequired"));
		Validate.notNull(solicitante, Message.get("licenca.solicitanteRequired"));

		this.protocolo = protocolo;
		this.solicitante = solicitante;
	}

	/**
	 * @return O Protocolo da licença
	 */
	public Protocolo protocolo() {
		return protocolo;
	}

	/**
	 * @return O Solicitante
	 */
	public Solicitante buscaSolicitante() {
		return solicitante;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameIdentityAs(Licenca other) {
		return other != null && protocolo.sameValueAs(other.protocolo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo identity() {
		return protocolo;
	}

	// --- Calculos internos

	// Surrugate key para o Hibernate
	@SuppressWarnings("unused")
	private Long id;
}
