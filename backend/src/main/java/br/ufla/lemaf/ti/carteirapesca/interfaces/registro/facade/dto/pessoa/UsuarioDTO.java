package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Usuario.
 */
@Data
@AllArgsConstructor
public class UsuarioDTO {

	private String email;

	private String login;

}
