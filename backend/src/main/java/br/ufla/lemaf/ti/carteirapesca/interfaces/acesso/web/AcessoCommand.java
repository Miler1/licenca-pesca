package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import lombok.Data;

/**
 * DTO do comando de acesso do {@link AcessoController}.
 */
@Data
public class AcessoCommand {

	private String cpf;

}
