package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class CabecalhoRetornoDTO {

	private static final DateTimeFormatter MASCARA_DATA_RETORNO = DateTimeFormatter.ofPattern("ddMMyy");

	private Integer identificacaoRegistro;
	private Integer identificacaoArquivoRetorno;
	private String literalRetorno;
	private String codigoServico;
	private String literalServico;
	private String codigoEmpresa;
	private String nomeEmpresa;
	private String numeroBanco;
	private String nomeBanco;
	private LocalDate dataGravacaoArquivo;
	private String densidadeGravacao;
	private String numeroAvisoBancario;
	private LocalDate dataCredito;
	private String numeroSequencialRegistro;

	public CabecalhoRetornoDTO(String cabecalho) {

		this.identificacaoRegistro = Integer.valueOf(cabecalho.substring(0, 1));
		this.identificacaoArquivoRetorno = Integer.valueOf(cabecalho.substring(1, 2));
		this.literalRetorno = cabecalho.substring(2, 9);
		this.codigoServico = cabecalho.substring(9, 11);
		this.literalServico = cabecalho.substring(11, 26);
		this.codigoEmpresa = cabecalho.substring(26, 46);
		this.nomeEmpresa = cabecalho.substring(46, 76);
		this.numeroBanco = cabecalho.substring(76, 79);
		this.nomeBanco = cabecalho.substring(79, 94);
		this.dataGravacaoArquivo = LocalDate.parse(cabecalho.substring(94, 100), MASCARA_DATA_RETORNO);
		this.densidadeGravacao = cabecalho.substring(100, 108);
		this.numeroAvisoBancario = cabecalho.substring(108, 113);

		String data = cabecalho.substring(379, 385);
		this.dataCredito = (StringUtils.isNotBlank(data) ? LocalDate.parse(cabecalho.substring(379, 385), MASCARA_DATA_RETORNO) : null );

		this.numeroSequencialRegistro = cabecalho.substring(394, 400);

	}


}
