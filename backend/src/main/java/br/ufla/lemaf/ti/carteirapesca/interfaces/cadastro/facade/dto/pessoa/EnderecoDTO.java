package br.ufla.lemaf.ti.carteirapesca.interfaces.cadastro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Endereço.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class EnderecoDTO {

	private Integer tipo;

	private ZonaLocalizacaoDTO zonaLocalizacao;

	private PaisDTO pais;

	private Boolean semNumero;

	private String logradouro;

	private String numero;

	private String bairro;

	private String complemento;

	private String cep;

	private MunicipioDTO municipio;

}
