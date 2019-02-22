package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoValorEfetivoEnum {

	VALOR_REAIS_MODULO_10(1, 6),
	QTD_MOEDAS_MODULO_10(2, 7),
	VALOR_REAIS_MODULO_11(3, 8),
	QTD_MOEDAS_MODULO_11(4, 9);

	private Integer id;
	private Integer codigo;

	TipoValorEfetivoEnum(Integer id, Integer codigo) {

		this.id = id;
		this.codigo = codigo;

	}

	public Integer getCodigo() {
		return codigo;
	}

}
