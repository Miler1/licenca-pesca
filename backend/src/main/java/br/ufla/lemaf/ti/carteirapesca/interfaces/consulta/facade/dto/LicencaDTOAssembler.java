package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;

import java.util.Date;

/**
 * Classe Assembler para LicencaDTO.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class LicencaDTOAssembler {

	/**
	 * LicencaDTO assembler.
	 *
	 * @param licenca A licença
	 * @return O DTO da licença
	 */
	public LicencaDTO toDTO(Licenca licenca) {
		if (licenca == null) return null;

		return new LicencaDTO(
			licenca.protocolo().getCodigoFormatado(),
			licenca.modalidade().getId(),
			licenca.status().getId(),
			setDate(licenca.dataCriacao()),
			setDate(licenca.dataAtivacao())
		);
	}

	/**
	 * Setter para date. Seguindo boas praticas de mutabilidade.
	 *
	 * @param date A data
	 * @return Um novo objeto de data
	 */
	private static Date setDate(final Date date) {
		if (date == null) return null;

		return new Date(date.getTime());
	}
}
