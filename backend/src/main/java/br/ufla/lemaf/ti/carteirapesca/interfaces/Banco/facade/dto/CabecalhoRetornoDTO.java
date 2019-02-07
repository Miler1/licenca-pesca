package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto;

import lombok.Getter;

@Getter
public class CabecalhoRetornoDTO {

	private Integer identificacaoRegistro;
	private Integer identificacaoArquivoRetorno;
	private String literalRetorno;
	private String codigoServico;
	private String literalServico;
	private String codigoEmpresa;
	private String nomeEmpresa;
	private String numeroBanco;
	private String nomeBanco;
	private String dataGravacaoArquivo;
	private String densidadeGravacao;
	private String numeroAvisoBancario;
	private String dataCredito;
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
		this.dataGravacaoArquivo = cabecalho.substring(94, 100);
		this.densidadeGravacao = cabecalho.substring(100, 108);
		this.numeroAvisoBancario = cabecalho.substring(108, 113);
		this.dataCredito = cabecalho.substring(379, 385);
		this.numeroSequencialRegistro = cabecalho.substring(394, 400);

	}


}
