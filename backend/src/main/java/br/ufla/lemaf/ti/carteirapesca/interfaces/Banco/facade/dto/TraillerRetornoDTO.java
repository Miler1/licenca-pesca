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

		this.identificacaoRegistro = Integer.valueOf(trailler.substring(0, 1));
		this.identificacaoArquivoRetorno = Integer.valueOf(trailler.substring(1, 2));
		this.indentificacaoTipoRegistro = trailler.substring(2, 4);
		this.codigoBanco = trailler.substring(4, 7);
		this.qtdTitulosCobranca = trailler.substring(17, 25);
		this.valorTotalCobranca = trailler.substring(25, 38);
		this.numeroAvisoBancario = trailler.substring(39, 47);

		this.qtdConfirmacaoEntrada = trailler.substring(57, 62);
		this.valorConfirmacaoEntrada = trailler.substring(62, 74);

		this.valorLiquidacao = trailler.substring(74, 86);
		this.qtdLiquidacao = trailler.substring(86, 91);

		this.valorRegistroOcorrencia = trailler.substring(91, 103);

		this.qtdTitulosBaixado = trailler.substring(103, 108);
		this.valorTitulosBaixado = trailler.substring(108, 120);

		this.qtdAbatimentoCancelamento = trailler.substring(120, 125);
		this.valorAbatimentoCancelamento = trailler.substring(125, 137);

		this.qtdVencimentoAlterado = trailler.substring(137, 142);
		this.valorVencimentoAlterado = trailler.substring(142, 154);

		this.qtdAbatimentoConcedido = trailler.substring(154, 159);
		this.valorAbatimentoConcedido = trailler.substring(159, 171);

		this.qtdConfirmacaoInstrucaoProtesto = trailler.substring(171, 176);
		this.valorConfirmacaoInstrucaoProtesto = trailler.substring(176, 188);

		this.valorTotalRateiosEfetuados = trailler.substring(362, 377);
		this.qtdTotalRateiosEfetuados = trailler.substring(377, 385);

		this.numeroSequencialRegistro = trailler.substring(394, 400);
	}

}
