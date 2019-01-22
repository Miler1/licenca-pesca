package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ValidacaoDTO {

	private String cpf;

	private String passaporte;

	private Date dataNascimento;

	private String nomeMae;


	@JsonCreator
	public ValidacaoDTO(@JsonProperty("cpf") String cpf,
						@JsonProperty("passaporte") String passaporte,
						@JsonProperty("dataNascimento") Date dataNascimento,
						@JsonProperty("nomeMae") String nomeMae) {
		this.cpf = (cpf != null ? CPFUtils.unformat(cpf) : null);
		this.passaporte = passaporte;
		this.dataNascimento = dataNascimento;
		this.nomeMae = nomeMae;
	}
}
