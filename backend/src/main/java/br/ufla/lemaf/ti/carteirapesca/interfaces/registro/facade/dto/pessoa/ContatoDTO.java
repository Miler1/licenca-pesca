package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Contato.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
class ContatoDTO {

	private Integer id;

	private TipoContatoDTO tipo;

	private String valor;

	private Boolean principal;

}
