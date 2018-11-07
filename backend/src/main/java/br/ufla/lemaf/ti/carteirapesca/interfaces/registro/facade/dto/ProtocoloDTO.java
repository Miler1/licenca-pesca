package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

/**
 * DTO do Protocolo da Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public final class ProtocoloDTO extends ResourceSupport {

	private String numero;

}
