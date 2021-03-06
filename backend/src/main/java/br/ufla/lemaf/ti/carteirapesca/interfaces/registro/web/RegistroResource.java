package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.InformacaoComplementarDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO de Recurso para o Registro de licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
public class RegistroResource {

	private PessoaDTO pessoa;

	private InformacaoComplementarDTO informacaoComplementar;

	private String protocolo;

	/**
	 * Construtor.
	 *
	 * @param pessoa                 O usuario solicitando a licença.
	 * @param informacaoComplementar Dados complementares para a licença.
	 */
	@JsonCreator
	public RegistroResource(@JsonProperty("pessoa") final PessoaDTO pessoa,
	                        @JsonProperty("informacaoComplementar")final InformacaoComplementarDTO informacaoComplementar,
							@JsonProperty("protocolo") final String protocolo) {
		this.pessoa = pessoa;
		this.informacaoComplementar = informacaoComplementar;
		this.protocolo = protocolo;
	}

}
