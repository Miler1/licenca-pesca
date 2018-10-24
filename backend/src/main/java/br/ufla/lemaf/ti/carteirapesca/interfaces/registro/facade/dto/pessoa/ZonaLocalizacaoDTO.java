package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

/**
 * DTO de Zona de Localização.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum ZonaLocalizacaoDTO {

	URBANA("URBANA", 0, "Urbana"),

	RURAL("RURAL", 1, "Rural");

	/**
	 * Construtor.
	 *
	 * @param nome      O nome da Zona de Localização.
	 * @param codigo    O código da Zona de Localização.
	 * @param descricao A descrição da Zona de Localização.
	 */
	ZonaLocalizacaoDTO(final String nome,
	                   final Integer codigo,
	                   final String descricao) {

		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;

	}

	private String nome;

	private Integer codigo;

	private String descricao;


	/**
	 * Gets nome.
	 *
	 * @return Value of nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gets descricao.
	 *
	 * @return Value of descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Gets codigo.
	 *
	 * @return Value of codigo.
	 */
	public Integer getCodigo() {
		return codigo;
	}

}
