package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

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

	private String municipio;

	private String mae;


	@JsonCreator
	public ValidacaoDTO(@JsonProperty("cpf") String cpf,
						@JsonProperty("passaporte") String passaporte,
						@JsonProperty("dataNascimento") Date dataNascimento,
						@JsonProperty("municipio") String municipio,
						@JsonProperty("mae") String mae) {
		this.cpf = cpf;
		this.passaporte = passaporte;
		this.dataNascimento = dataNascimento;
		this.municipio = municipio;
		this.mae = mae;
	}
}
