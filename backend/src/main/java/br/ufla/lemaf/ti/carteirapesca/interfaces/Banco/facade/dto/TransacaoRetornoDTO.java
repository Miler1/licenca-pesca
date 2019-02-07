package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto;

import lombok.Getter;

@Getter
public class TransacaoRetornoDTO {

	private Integer identificacaoRegistro;
	private String tipoInscricaoEmpresa;
	private String numeroInscricaoEmpresa;
	private String identificacaoEmpresaBeneficiario;
	private String numeroControleParticipante;
	private String nossoNumero;
	private String identificacaoRateioCredito;
	private String pagamentoParcial;
	private String carteira;
	private String identificacaoOcorrencia;
	private String dataOcorrencia;
	private String numeroDocumento;
	private String identificacaoTituloBanco;
	private String dataVencimentoTitulo;
	private String valorTitulo;
	private String codigoBancoCobrador;
	private String agenciaCobradora;
	private String despesasCodigoOcorrencia;
	private String outrasDespesasCustasProtesto;
	private String jurosAtraso;
	private String iofDevido;
	private String abatimentoConcedido;
	private String descontoConcedido;
	private String valorPago;
	private String jurosMora;
	private String outrosCreditos;
	private String motivoCodigoOcorrencia;
	private String dataCredito;
	private String origemPagamento;
	private String motivosRejeicao;
	private String numeroCartorio;
	private String numeroProtocolo;
	private String numeroSequecialRegistro;


	//TODO mapear campos "Despesas de cobranca"
	public TransacaoRetornoDTO(String transacao) {

		this.identificacaoRegistro = Integer.valueOf(transacao.substring(0, 1));
		this.tipoInscricaoEmpresa = transacao.substring(1, 3);
		this.numeroInscricaoEmpresa = transacao.substring(3, 17);
		this.identificacaoEmpresaBeneficiario = transacao.substring(20, 37);
		this.numeroControleParticipante = transacao.substring(37, 62);
		this.nossoNumero = transacao.substring(70, 82);
		this.identificacaoRateioCredito = transacao.substring(104, 105);
		this.pagamentoParcial = transacao.substring(105, 107);
		this.carteira = transacao.substring(107, 108);
		this.identificacaoOcorrencia = transacao.substring(108, 110);
		this.dataOcorrencia = transacao.substring(110, 116);
		this.numeroDocumento = transacao.substring(116, 126);
		this.identificacaoTituloBanco = transacao.substring(126, 146);
		this.dataVencimentoTitulo = transacao.substring(146, 152);
		this.valorTitulo = transacao.substring(152, 165);
		this.codigoBancoCobrador = transacao.substring(165, 168);
		this.agenciaCobradora = transacao.substring(168, 173);
		this.despesasCodigoOcorrencia = transacao.substring(175, 188);
		this.outrasDespesasCustasProtesto = transacao.substring(188, 201);
		this.jurosAtraso = transacao.substring(201, 214);
		this.iofDevido = transacao.substring(214, 227);
		this.abatimentoConcedido = transacao.substring(227, 240);
		this.descontoConcedido = transacao.substring(240, 253);
		this.valorPago = transacao.substring(253, 266);
		this.jurosMora = transacao.substring(266, 279);
		this.outrosCreditos = transacao.substring(279, 292);
		this.motivoCodigoOcorrencia = transacao.substring(294, 295);
		this.dataCredito = transacao.substring(295, 301);
		this.origemPagamento = transacao.substring(301, 304);
		this.motivosRejeicao = transacao.substring(318, 328);
		this.numeroCartorio = transacao.substring(368, 370);
		this.numeroProtocolo = transacao.substring(370, 380);
		this.numeroSequecialRegistro = transacao.substring(394, 400);

	}

}
