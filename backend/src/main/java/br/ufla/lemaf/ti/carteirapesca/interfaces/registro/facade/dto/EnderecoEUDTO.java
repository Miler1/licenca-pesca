package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class EnderecoEUDTO {

	private Integer tipo;

	private Integer zonaLocalizacao;

	private Boolean semNumero;

	private String logradouro;

	private Integer numero;

	private String bairro;

	private String complemento;

	private String cep;

	private String uf;

	private Integer municipio;

	private String descricaoAcesso;

	/**
	 * Construtor de endereço.
	 *
	 * @param tipo            O tipo de endereço
	 * @param zonaLocalizacao A zona de localização
	 * @param semNumero       {@code true} se não existir numero
	 * @param logradouro      O logradouro
	 * @param numero          O numero do endereço
	 * @param bairro          O bairro
	 * @param complemento     O complemento
	 * @param cep             O CEP
	 * @param uf              A unidade federativa
	 * @param municipio       O código do municipio no IBGE
	 * @param descricaoAcesso A descrição de acesso
	 */
	@JsonCreator
	EnderecoEUDTO(@JsonProperty("tipo") final Integer tipo,
				@JsonProperty("zonaLocalizacao") final Integer zonaLocalizacao,
				@JsonProperty("semNumero") final Boolean semNumero,
				@JsonProperty("logradouro") final String logradouro,
				@JsonProperty("numero") final Integer numero,
				@JsonProperty("bairro") final String bairro,
				@JsonProperty("complemento") final String complemento,
				@JsonProperty("cep") final String cep,
				@JsonProperty("uf") final String uf,
				@JsonProperty("municipio") final Integer municipio,
				@JsonProperty("descricaoAcesso") final String descricaoAcesso) {
		this.tipo = tipo;
		this.zonaLocalizacao = zonaLocalizacao;
		this.semNumero = semNumero;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
		this.uf = uf;
		this.municipio = municipio;
		this.descricaoAcesso = descricaoAcesso;
	}
}
