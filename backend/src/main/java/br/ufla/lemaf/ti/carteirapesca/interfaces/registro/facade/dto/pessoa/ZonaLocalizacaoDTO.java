package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Zona de Localização.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
class ZonaLocalizacaoDTO {

	private String nome;

	private Integer codigo;

	private String descricao;

}
