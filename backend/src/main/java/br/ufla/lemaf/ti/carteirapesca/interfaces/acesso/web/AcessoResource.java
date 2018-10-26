package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO de Recurso do Usuario para o acesso do {@link AcessoController}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
public class AcessoResource {

	@ApiModelProperty(notes = "O CPF do usuário")
	private String cpf;

	@ApiModelProperty(notes = "O passaporte do usuário")
	private String passaporte;

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
		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	@Override
	public String toString() {
		return String.format("Usuario{cpf='%s', passaporte='%s'}", cpf, passaporte);
	}

}
