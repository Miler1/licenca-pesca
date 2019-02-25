package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class TransacaoRetornoDTO {

	private static final DateTimeFormatter MASCARA_DATA_RETORNO = DateTimeFormatter.ofPattern("ddMMyy");

	private Integer identificacaoRegistro;
	private String tipoInscricaoEmpresa;
	private String numeroInscricaoEmpresa;
	private String identificacaoEmpresaBeneficiario;
	private String numeroControleParticipante;
	private String nossoNumeroComDigito;
	private String identificacaoRateioCredito;
	private String pagamentoParcial;
	private String carteira;
	private String identificacaoOcorrencia;
	private LocalDate dataOcorrencia;
	private Integer numeroDocumento;
	private String identificacaoTituloBanco;
	private LocalDate dataVencimentoTitulo;
	private BigDecimal valorTitulo;
	private String codigoBancoCobrador;
	private String agenciaCobradora;
	private BigDecimal despesasCodigoOcorrencia;
	private BigDecimal outrasDespesasCustasProtesto;
	private BigDecimal jurosAtraso;
	private BigDecimal iofDevido;
	private BigDecimal abatimentoConcedido;
	private BigDecimal descontoConcedido;
	private BigDecimal valorPago;
	private BigDecimal jurosMora;
	private BigDecimal outrosCreditos;
	private String motivoCodigoOcorrencia;
	private LocalDate dataCredito;
	private String origemPagamento;
	private String motivosRejeicao;
	private String numeroCartorio;
	private String numeroProtocolo;
	private String numeroSequecialRegistro;

	public TransacaoRetornoDTO(String transacao) {

		this.identificacaoRegistro = Integer.valueOf(transacao.substring(0, 1));
		this.tipoInscricaoEmpresa = transacao.substring(1, 3);
		this.numeroInscricaoEmpresa = transacao.substring(3, 17);
		this.identificacaoEmpresaBeneficiario = transacao.substring(20, 37);
		this.numeroControleParticipante = transacao.substring(37, 62);
		this.nossoNumeroComDigito = transacao.substring(70, 82);
		this.identificacaoRateioCredito = transacao.substring(104, 105);
		this.pagamentoParcial = transacao.substring(105, 107);
		this.carteira = transacao.substring(107, 108);
		this.identificacaoOcorrencia = transacao.substring(108, 110);
		this.dataOcorrencia = LocalDate.parse(transacao.substring(110, 116), MASCARA_DATA_RETORNO);
		this.numeroDocumento = Integer.valueOf(transacao.substring(116, 126).replaceAll(" ", ""));
		this.identificacaoTituloBanco = transacao.substring(126, 146);
		this.dataVencimentoTitulo = LocalDate.parse(transacao.substring(146, 152), MASCARA_DATA_RETORNO);
		this.valorTitulo = new BigDecimal(Double.valueOf(transacao.substring(152, 165)) /100);
		this.codigoBancoCobrador = transacao.substring(165, 168);
		this.agenciaCobradora = transacao.substring(168, 173);
		this.despesasCodigoOcorrencia = new BigDecimal(Double.valueOf(transacao.substring(175, 188)) /100);
		this.outrasDespesasCustasProtesto = new BigDecimal(Double.valueOf(transacao.substring(188, 201)) /100);
		this.jurosAtraso = new BigDecimal(Double.valueOf(transacao.substring(201, 214)) /100);
		this.iofDevido = new BigDecimal(Double.valueOf(transacao.substring(214, 227)) /100);
		this.abatimentoConcedido = new BigDecimal(Double.valueOf(transacao.substring(227, 240)) /100);
		this.descontoConcedido = new BigDecimal(Double.valueOf(transacao.substring(240, 253)) /100);
		this.valorPago = new BigDecimal(Double.valueOf(transacao.substring(253, 266)) /100);
		this.jurosMora = new BigDecimal(Double.valueOf(transacao.substring(266, 279)) /100);
		this.outrosCreditos = new BigDecimal(Double.valueOf(transacao.substring(279, 292)) /100);
		this.motivoCodigoOcorrencia = transacao.substring(294, 295);

		String data = transacao.substring(295, 301);
		this.dataCredito = (StringUtils.isNotBlank(data) ? LocalDate.parse(transacao.substring(295, 301), MASCARA_DATA_RETORNO) : null );

		this.origemPagamento = transacao.substring(301, 304);
		this.motivosRejeicao = transacao.substring(318, 328);
		this.numeroCartorio = transacao.substring(368, 370);
		this.numeroProtocolo = transacao.substring(370, 380);
		this.numeroSequecialRegistro = transacao.substring(394, 400);

	}

}
