package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoArquivoEnum {

	BOLETO(1, "BOLETO"),
	REMESSA(2, "REMESSA"),
	RETORNO(3, "RETORNO");

	private Integer id;
	private String codigo;

	TipoArquivoEnum(Integer id, String codigo) {

		this.id = id;
		this.codigo = codigo;

	}

	public String getCodigo() {
		return codigo;
	}

}
