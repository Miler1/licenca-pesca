package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.questionario.InformacaoComplementarDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO de Recurso para o Registro de licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
public class RegistroResource {

	@ApiModelProperty(notes = "Dados do usuário.")
	private PessoaDTO pessoa;

	@ApiModelProperty(notes = "Dados complementáres do usuario.")
	private InformacaoComplementarDTO informacaoComplementar;

	/**
	 * Construtor.
	 *
	 * @param pessoa                 O usuario solicitando a licença.
	 * @param informacaoComplementar Dados complementares para a licença.
	 */
	@JsonCreator
	public RegistroResource(@JsonProperty("usuario") PessoaDTO pessoa,
	                        @JsonProperty("questionario")
		                        InformacaoComplementarDTO informacaoComplementar) {
		this.pessoa = pessoa;
		this.informacaoComplementar = informacaoComplementar;
	}

}
