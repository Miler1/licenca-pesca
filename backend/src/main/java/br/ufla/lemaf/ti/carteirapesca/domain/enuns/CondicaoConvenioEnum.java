package br.ufla.lemaf.ti.carteirapesca.domain.enuns;

public enum CondicaoConvenioEnum {

	AGUARDANDO_PAGAMENTO(1, "AGUARDANDO_PAGAMENTO"),
	PAGO(2, "PAGO"),
	VENCIDO(3, "VENCIDO");

	private Integer id;
	private String codigo;

	CondicaoConvenioEnum(Integer id, String codigo) {

		this.id = id;
		this.codigo = codigo;

	}

	public String getCodigo() {
		return codigo;
	}

}
