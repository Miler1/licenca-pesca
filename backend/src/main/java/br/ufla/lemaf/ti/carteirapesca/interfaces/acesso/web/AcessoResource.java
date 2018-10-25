package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Recurso do Usuario para o acesso do {@link AcessoController}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class AcessoResource {

	@ApiModelProperty(notes = "O CPF do usuário")
	private String cpf;

	@ApiModelProperty(notes = "O passaporte do usuário")
	private String passaporte;

	@Override
	public String toString() {
		return String.format("Usuario{cpf='%s', passaporte='%s'}", cpf, passaporte);
	}

}
