package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

public class LicencaPescaDTO {

	private Pessoa pessoa;

	private Licenca licenca;

	private Protocolo protocolo;

	/**
	 * Construtor.
	 */

	@JsonCreator
	public LicencaPescaDTO(@JsonProperty("pessoa") final Pessoa pessoa,
						   @JsonProperty("licenca") final Licenca licenca,
						   @JsonProperty("protocolo") final Protocolo protocolo) {

		this.pessoa = pessoa;
		this.licenca = licenca;
		this.protocolo = protocolo;
	}

}
