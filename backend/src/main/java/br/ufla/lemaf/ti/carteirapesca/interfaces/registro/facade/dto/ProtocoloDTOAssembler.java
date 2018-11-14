package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo;

/**
 * Assembler do DTO de Protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ProtocoloDTOAssembler {

	/**
	 * Método Assembler do DTO de Protocolo.
	 *
	 * @param protocolo O protocolo da licença
	 * @return O DTO de Protocolo
	 */
	public ProtocoloDTO toDTO(final Protocolo protocolo) {
		if (protocolo == null) return null;

		return new ProtocoloDTO(protocolo.toString());
	}
}
