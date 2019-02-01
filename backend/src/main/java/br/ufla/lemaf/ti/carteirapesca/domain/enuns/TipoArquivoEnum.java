package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum TipoArquivoEnum {

	BOLETO(1, "BOLETO", "Arquivo pdf do boleto que foi gerado"),
	REMESSA(2, "REMESSA", "Arquivo com os dados dos boletos que são enviados ao banco"),
	RETORNO(3, "RETORNO", "Arquivo com os dados dos boletos que foram processador pelo banco");

	private Integer id;
	private String codigo;
	private String descricao;

	TipoArquivoEnum(Integer id, String codigo, String descricao) {

		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;

	}

	public String getCodigo() {
		return codigo;
	}


}
