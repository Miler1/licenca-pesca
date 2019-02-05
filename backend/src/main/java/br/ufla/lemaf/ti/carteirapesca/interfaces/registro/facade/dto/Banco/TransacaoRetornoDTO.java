package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.Banco;

public class TransacaoRetornoDTO {

	private Integer identificacaoRegistro;
	private String tipoInscricaoEmpresa;
	private String numeroInscricaoEmpresa;
	private String identificacaoEmpresaBeneficiario;
	private String numeroControleParticipante;
	private String nossoNumero;
	private String identificacaoRateioCredito;
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

	public TransacaoRetornoDTO(String transacao) {

		this.identificacaoRegistro = Integer.valueOf(transacao.substring(1, 2));
		this.tipoInscricaoEmpresa = transacao.substring(2, 4);
		this.numeroInscricaoEmpresa = transacao.substring(4, 18);
		this.identificacaoEmpresaBeneficiario = transacao.substring(21, 38);
		this.numeroControleParticipante = transacao.substring(38, 63);
		this.nossoNumero = transacao.substring(71, 83);
		this.identificacaoRateioCredito = transacao.substring(105, 106);
		this.carteira = transacao.substring(108, 109);
		this.identificacaoOcorrencia = transacao.substring(109, 111);
		this.dataOcorrencia = transacao.substring(111, 117);
		this.numeroDocumento = transacao.substring(117, 127);
		this.dataVencimentoTitulo = transacao.substring(147, 153);
		this.valorTitulo = transacao.substring(147, 153);
		this.codigoBancoCobrador = transacao.substring(166, 169);
		this.agenciaCobradora = transacao.substring(169, 174);
		this.despesasCodigoOcorrencia = transacao.substring(176, 189);
		this.outrasDespesasCustasProtesto = transacao.substring(189, 202);
		this.jurosAtraso = transacao.substring(202, 215);
		this.iofDevido = transacao.substring(215, 228);
		this.abatimentoConcedido = transacao.substring(228, 241);
		this.descontoConcedido = transacao.substring(241, 254);
		this.valorPago = transacao.substring(254, 267);
		this.jurosMora = transacao.substring(267, 280);
		this.outrosCreditos = transacao.substring(280, 293);
		this.motivoCodigoOcorrencia = transacao.substring(295, 296);
		this.dataCredito = transacao.substring(296, 302);
		this.origemPagamento = transacao.substring(302, 305);
		this.motivosRejeicao = transacao.substring(319, 329);
		this.numeroCartorio = transacao.substring(369, 371);
		this.numeroProtocolo = transacao.substring(371, 381);
		this.numeroSequecialRegistro = transacao.substring(395, 401);

	}

}
