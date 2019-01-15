package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;

@Getter
public class LicencaPescaDTO {

	private Pessoa pessoa;

	private Licenca licenca;

	private Protocolo protocolo;

	/**
	 * Construtor.
	 */

	@JsonCreator
	public LicencaPescaDTO(@JsonProperty("licenca") final Licenca licenca,
						   @JsonProperty("protocolo") final Protocolo protocolo,
						   @JsonProperty("pessoa") final Pessoa pessoa) {

		this.licenca = licenca;
		this.protocolo = protocolo;
		this.pessoa = pessoa;

	}

}
