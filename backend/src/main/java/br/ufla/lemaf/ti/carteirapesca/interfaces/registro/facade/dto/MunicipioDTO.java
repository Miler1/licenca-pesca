package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MunicipioDTO {

	private Integer municipio;

	/**
	 * Construtor de municipio.
	 * @param municipio
	 */

	@JsonCreator
	MunicipioDTO(@JsonProperty("municipo") final Integer municipio) {

		this.municipio = municipio;
	}
}
