package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ValidacaoDTO {

	private AcessoResource acessoResource;

	private Date dataNascimento;

	private String nomeMae;

	public AcessoResource getAcessoResource() {
		return acessoResource;
	}

	@JsonCreator
	public ValidacaoDTO(@JsonProperty("dataNascimento") Date dataNascimento,
						@JsonProperty("nomeMae") String nomeMae) {
		this.dataNascimento = dataNascimento;
		this.nomeMae = nomeMae;
	}

	public ValidacaoDTO (AcessoResource acessoResource){
		this.acessoResource = acessoResource;
	}
}
