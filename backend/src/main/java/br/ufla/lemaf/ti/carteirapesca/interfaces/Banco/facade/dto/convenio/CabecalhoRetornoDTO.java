package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class CabecalhoRetornoDTO {

	private static final DateTimeFormatter MASCARA_DATA_RETORNO = DateTimeFormatter.ofPattern("YYYYMMdd");

	private String codigoRegistro;
	private Integer codigoRemessa;
	private Integer codigoConvenio;
	private String nomeEmpresa;
	private Integer codigoBanco;
	private String nomeBanco;
	private LocalDate dataGeracaoArquivo;
	private Integer sequencialArquivo;
	private Integer versaoLayout;
	private String codigoBarras;
	private String reservadoFuturo;

	public CabecalhoRetornoDTO(String cabecalho) {

		this.codigoRegistro = cabecalho.substring(0, 1);
		this.codigoRemessa = Integer.valueOf(cabecalho.substring(1, 2));
		this.codigoConvenio = Integer.valueOf(cabecalho.substring(2, 22));
		this.nomeEmpresa = cabecalho.substring(22, 42);
		this.codigoBanco = Integer.valueOf(cabecalho.substring(42, 45));
		this.nomeBanco = cabecalho.substring(45, 65);
		this.dataGeracaoArquivo = LocalDate.parse(cabecalho.substring(94, 100), MASCARA_DATA_RETORNO);
		this.sequencialArquivo = Integer.valueOf(cabecalho.substring(72, 79));
		this.versaoLayout = Integer.valueOf(cabecalho.substring(79, 81));
		this.codigoBarras = cabecalho.substring(81, 98);

	}

}
