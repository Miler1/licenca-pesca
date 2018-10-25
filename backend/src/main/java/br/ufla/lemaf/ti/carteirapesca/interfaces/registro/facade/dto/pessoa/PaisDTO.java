package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

/**
 * DTO de País.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
class PaisDTO extends ResourceSupport {

	private Integer id;

	private String nome;

	private String sigla;

}
