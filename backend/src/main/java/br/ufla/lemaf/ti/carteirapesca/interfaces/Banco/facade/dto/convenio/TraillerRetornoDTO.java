package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TraillerRetornoDTO {

	private String codigoRegistro;
	private Integer qtdRegistros;
	private BigDecimal vlrRegistros;

	public TraillerRetornoDTO() {}

	public TraillerRetornoDTO(String trailler) {

		this.codigoRegistro = trailler.substring(0, 1);
		this.qtdRegistros = Integer.valueOf(trailler.substring(1, 7));
		this.vlrRegistros = new BigDecimal(Double.valueOf(trailler.substring(7, 24)) /100);

	}

	public void validaTrailler(String cabecalho) throws Exception {

		this.codigoRegistro = cabecalho.substring(0, 1);

		if(!this.codigoRegistro.equals("Z")) {
			throw new Exception("TRAILLER do arquivo de retorno inválido");
		}

	}

}
