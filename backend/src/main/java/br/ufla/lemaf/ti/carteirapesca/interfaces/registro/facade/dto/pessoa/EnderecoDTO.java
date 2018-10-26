package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO de Endereço.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
class EnderecoDTO {

	private Integer tipo;

	private ZonaLocalizacaoDTO zonaLocalizacao;

	private PaisDTO pais;

	private Boolean semNumero;

	private String logradouro;

	private Integer numero;

	private String bairro;

	private String complemento;

	private String cep;

	private MunicipioDTO municipio;

}
