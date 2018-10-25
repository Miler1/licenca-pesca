package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

/**
 * DTO de Sexo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
class SexoDTO extends ResourceSupport {

	private String nome;

	private Integer codigo;

	private String descricao;

}
