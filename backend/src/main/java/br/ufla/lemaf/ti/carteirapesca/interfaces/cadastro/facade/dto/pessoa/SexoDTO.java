package br.ufla.lemaf.ti.carteirapesca.interfaces.cadastro.facade.dto.pessoa;

/**
 * DTO de Sexo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum SexoDTO {

	MASCULINO("MASCULINO", 0, "Masculino"),

	FEMININO("FEMININO", 1, "Feminino"),

	OUTROS("OUTROS", 2, "Outros");

	/**
	 * Construtor.
	 *
	 * @param nome      O nome do sexo.
	 * @param codigo    O código do sexo.
	 * @param descricao A descrição do sexo.
	 */
	SexoDTO(final String nome,
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
