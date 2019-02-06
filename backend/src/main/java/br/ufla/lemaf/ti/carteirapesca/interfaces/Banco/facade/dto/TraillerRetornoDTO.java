package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto;

import lombok.Getter;

@Getter
public class TraillerRetornoDTO {

	private Integer identificacaoRegistro;
	private Integer identificacaoArquivoRetorno;
	private String indentificacaoTipoRegistro;
	private String codigoBanco;
	private String qtdTitulosCobranca;
	private String valorTotalCobranca;
	private String numeroAvisoBancario;

	private String qtdConfirmacaoEntrada;
	private String valorConfirmacaoEntrada;

	private String valorLiquidacao;
	private String qtdLiquidacao;

	private String valorRegistroOcorrencia;

	private String qtdTitulosBaixado;
	private String valorTitulosBaixado;

	private String qtdAbatimentoCancelamento;
	private String valorAbatimentoCancelamento;

	private String qtdVencimentoAlterado;
	private String valorVencimentoAlterado;

	private String qtdAbatimentoConcedido;
	private String valorAbatimentoConcedido;

	private String qtdConfirmacaoInstrucaoProtesto;
	private String valorConfirmacaoInstrucaoProtesto;

	private String valorTotalRateiosEfetuados;
	private String qtdTotalRateiosEfetuados;

	private String numeroSequencialRegistro;

	public TraillerRetornoDTO(String trailler) {

		this.identificacaoRegistro = Integer.valueOf(trailler.substring(1, 2));
		this.identificacaoArquivoRetorno = Integer.valueOf(trailler.substring(2, 3));
		this.indentificacaoTipoRegistro = trailler.substring(3, 5);
		this.codigoBanco = trailler.substring(5, 8);
		this.qtdTitulosCobranca = trailler.substring(18, 26);
		this.valorTotalCobranca = trailler.substring(26, 39);
		this.numeroAvisoBancario = trailler.substring(40, 48);

		this.qtdConfirmacaoEntrada = trailler.substring(58, 63);
		this.valorConfirmacaoEntrada = trailler.substring(63, 75);

		this.valorLiquidacao = trailler.substring(75, 87);
		this.qtdLiquidacao = trailler.substring(87, 92);

		this.valorRegistroOcorrencia = trailler.substring(92, 104);

		this.qtdTitulosBaixado = trailler.substring(104, 109);
		this.valorTitulosBaixado = trailler.substring(109, 121);

		this.qtdAbatimentoCancelamento = trailler.substring(121, 126);
		this.valorAbatimentoCancelamento = trailler.substring(126, 138);

		this.qtdVencimentoAlterado = trailler.substring(138, 143);
		this.valorVencimentoAlterado = trailler.substring(143, 155);

		this.qtdAbatimentoConcedido = trailler.substring(155, 160);
		this.valorAbatimentoConcedido = trailler.substring(160, 172);

		this.qtdConfirmacaoInstrucaoProtesto = trailler.substring(172, 177);
		this.valorConfirmacaoInstrucaoProtesto = trailler.substring(177, 189);

		this.valorTotalRateiosEfetuados = trailler.substring(363, 378);
		this.qtdTotalRateiosEfetuados = trailler.substring(378, 386);

		this.numeroSequencialRegistro = trailler.substring(395, 400);
	}

}
