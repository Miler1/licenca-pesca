package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoSegmentoEnum {

	PREFEITURA(1, 1),
	SANEAMENTO(2, 2),
	ENERGIA_GAS(3, 3),
	TELECOMUNICACAO(4, 4),
	ORGAO_GOVERNAMENTAL(5, 5),
	DEMAIS_EMPRESAS(6, 6),
	MULTAS_TRANSITO(7, 7),
	USO_EXCLUSIVO_BANCO(8, 9);

	private Integer id;
	private Integer codigo;

	TipoSegmentoEnum(Integer id, Integer codigo) {

		this.id = id;
		this.codigo = codigo;

	}

	public Integer getCodigo() {
		return codigo;
	}

}
