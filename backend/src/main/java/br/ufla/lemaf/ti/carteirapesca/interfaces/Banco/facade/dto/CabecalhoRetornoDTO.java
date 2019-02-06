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

		this.identificacaoRegistro = Integer.valueOf(cabecalho.substring(1, 2));
		this.identificacaoArquivoRetorno = Integer.valueOf(cabecalho.substring(2, 3));
		this.literalRetorno = cabecalho.substring(3, 10);
		this.codigoServico = cabecalho.substring(10, 12);
		this.literalServico = cabecalho.substring(12, 27);
		this.codigoEmpresa = cabecalho.substring(27, 46);
		this.nomeEmpresa = cabecalho.substring(47, 77);
		this.numeroBanco = cabecalho.substring(77, 80);
		this.nomeBanco = cabecalho.substring(80, 95);
		this.dataGravacaoArquivo = cabecalho.substring(95, 101);
		this.densidadeGravacao = cabecalho.substring(101, 109);
		this.numeroAvisoBancario = cabecalho.substring(109, 114);
		this.dataCredito = cabecalho.substring(380, 386);
		this.numeroSequencialRegistro = cabecalho.substring(395, 401);

	}


}
