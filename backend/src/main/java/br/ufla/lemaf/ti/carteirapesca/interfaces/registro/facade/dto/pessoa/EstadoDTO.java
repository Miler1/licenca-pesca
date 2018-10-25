package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Estado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
class EstadoDTO {

	private Integer id;

	private String sigla;

	private String nome;

}