package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio;

import java.math.BigDecimal;

public class TraillerRetornoDTO {

	private String codigoRegistro;
	private Integer qtdRegistros;
	private BigDecimal vlrRegistros;

	public TraillerRetornoDTO(String trailler) {

		this.codigoRegistro = trailler.substring(0, 1);
		this.qtdRegistros = Integer.valueOf(trailler.substring(1, 7));
		this.vlrRegistros = new BigDecimal(Double.valueOf(trailler.substring(7, 24)) /100);

	}

}
