package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.questionario;

import lombok.Data;

/**
 * Classe base contendo atributos recorrentes
 * em DTOs para tipos.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
abstract class BaseTipoGenerico {

	private Integer codigo;

	private String nome;

}
