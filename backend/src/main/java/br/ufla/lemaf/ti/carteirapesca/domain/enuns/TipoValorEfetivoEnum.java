package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoValorEfetivoEnum {

	VALOR_REAIS_MODULO_10(1, 1),
	QTD_MOEDAS_MODULO_10(2, 2),
	VALOR_REAIS_MODULO_11(3, 3),
	QTD_MOEDAS_MODULO_11(4, 4);

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
