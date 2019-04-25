package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoArquivoEnum {

	BOLETO(1, "BOLETO"),
	REMESSA(2, "REMESSA"),
	RETORNO(3, "RETORNO"),
	DOCUMENTO_ARRECADACAO(4, "DOCUMENTO_ARRECADACAO"),
	RETORNO_ARRECADACAO(5, "RETORNO_ARRECADACAO");

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
