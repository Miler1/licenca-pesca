package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO de Municipio.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
class MunicipioDTO {

	private Integer id;

	private String nome;

	private EstadoDTO estado;

	private Integer codigoIbge;


}
