package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TraillerRetornoDTO {

	private Integer identificacaoRegistro;
	private Integer identificacaoArquivoRetorno;
	private String indentificacaoTipoRegistro;
	private String codigoBanco;
	private Integer qtdTitulosCobranca;
	private BigDecimal valorTotalCobranca;
	private Integer numeroAvisoBancario;

	private Integer qtdConfirmacaoEntrada;
	private BigDecimal valorConfirmacaoEntrada;

	private BigDecimal valorLiquidacao;
	private Integer qtdLiquidacao;

	private BigDecimal valorRegistroOcorrencia;

	private Integer qtdTitulosBaixado;
	private BigDecimal valorTitulosBaixado;

	private Integer qtdAbatimentoCancelamento;
	private BigDecimal valorAbatimentoCancelamento;

	private Integer qtdVencimentoAlterado;
	private BigDecimal valorVencimentoAlterado;

	private Integer qtdAbatimentoConcedido;
	private BigDecimal valorAbatimentoConcedido;

	private Integer qtdConfirmacaoInstrucaoProtesto;
	private BigDecimal valorConfirmacaoInstrucaoProtesto;

	private BigDecimal valorTotalRateioEfetuado;
	private Integer qtdTotalRateioEfetuado;

	private String numeroSequencialRegistro;

	public TraillerRetornoDTO(String trailler) {

		this.identificacaoRegistro = Integer.valueOf(trailler.substring(0, 1));
		this.identificacaoArquivoRetorno = Integer.valueOf(trailler.substring(1, 2));
		this.indentificacaoTipoRegistro = trailler.substring(2, 4);
		this.codigoBanco = trailler.substring(4, 7);
		this.qtdTitulosCobranca = Integer.valueOf(trailler.substring(17, 25));
		this.valorTotalCobranca = new BigDecimal(Double.valueOf(trailler.substring(25, 38)) /100);
		this.numeroAvisoBancario = Integer.valueOf(trailler.substring(39, 47));

		this.qtdConfirmacaoEntrada = Integer.valueOf(trailler.substring(57, 62));
		this.valorConfirmacaoEntrada = new BigDecimal(Double.valueOf(trailler.substring(62, 74)) /100);

		this.valorLiquidacao = new BigDecimal(Double.valueOf(trailler.substring(74, 86)) /100);
		this.qtdLiquidacao = Integer.valueOf(trailler.substring(86, 91));

		this.valorRegistroOcorrencia = new BigDecimal(Double.valueOf(trailler.substring(91, 103)) /100);

		this.qtdTitulosBaixado = Integer.valueOf(trailler.substring(103, 108));
		this.valorTitulosBaixado = new BigDecimal(Double.valueOf(trailler.substring(108, 120)) /100);

		this.qtdAbatimentoCancelamento = Integer.valueOf(trailler.substring(120, 125));
		this.valorAbatimentoCancelamento = new BigDecimal(Double.valueOf(trailler.substring(125, 137)) /100);

		this.qtdVencimentoAlterado = Integer.valueOf(trailler.substring(137, 142));
		this.valorVencimentoAlterado = new BigDecimal(Double.valueOf(trailler.substring(142, 154)) /100);

		this.qtdAbatimentoConcedido = Integer.valueOf(trailler.substring(154, 159));
		this.valorAbatimentoConcedido = new BigDecimal(Double.valueOf(trailler.substring(159, 171)) /100);

		this.qtdConfirmacaoInstrucaoProtesto = Integer.valueOf(trailler.substring(171, 176));
		this.valorConfirmacaoInstrucaoProtesto = new BigDecimal(Double.valueOf(trailler.substring(176, 188)) /100);

		this.valorTotalRateioEfetuado = new BigDecimal(Double.valueOf(trailler.substring(362, 377)) /100);
		this.qtdTotalRateioEfetuado = Integer.valueOf(trailler.substring(377, 385));

		this.numeroSequencialRegistro = trailler.substring(394, 400);
	}

}
