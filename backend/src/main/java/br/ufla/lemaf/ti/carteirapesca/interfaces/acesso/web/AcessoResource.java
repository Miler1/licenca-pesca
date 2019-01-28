package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AcessoResource {

	private ValidacaoDTO validacaoDTO;

	private String cpf;

	private String passaporte;

	public ValidacaoDTO getValidacaoDTO() {
		return validacaoDTO;
	}

	/**
	 * Construtor.
	 *
	 * @param cpf        O CPF para o acesso
	 * @param passaporte O Passaprte para o acesso
	 * @apiNote Necessário a implementação para mostrar
	 * ao JSON como serializar o objeto.
	 */
	@JsonCreator
	public AcessoResource(@JsonProperty("cpf") String cpf,
						  @JsonProperty("passaporte") String passaporte) {

		this.cpf = "13090989646";
		this.passaporte = passaporte;
	}

	@Override
	public String toString() {
		return String.format("Usuario{cpf='%s', passaporte='%s'}", cpf, passaporte);
	}

}
