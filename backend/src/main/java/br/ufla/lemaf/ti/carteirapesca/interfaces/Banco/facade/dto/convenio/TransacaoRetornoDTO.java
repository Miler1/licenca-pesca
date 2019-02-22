package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class TransacaoRetornoDTO {

	private static final DateTimeFormatter MASCARA_DATA_RETORNO = DateTimeFormatter.ofPattern("YYYYMMdd");

	private String codigoRegistro;
	private String idAgenciaContaDigitoCreditada;
	private LocalDate dataPamento;
	private LocalDate dataCredito;
	private String codigoBarras;
	private BigDecimal valorRecebido;
	private BigDecimal valorTarifa;
	private Integer numeroSequencialRegistro;
	private String codigoAgenciaArreacadadora;
	private String formaArrecadacao;
	private String codigoTransacao;
	private Integer formaParamento;

	public TransacaoRetornoDTO(String transacao) {

		this.codigoRegistro = transacao.substring(0, 1);
		this.idAgenciaContaDigitoCreditada = transacao.substring(1, 21);
		this.dataPamento = LocalDate.parse(transacao.substring(21, 29), MASCARA_DATA_RETORNO);
		this.dataCredito = LocalDate.parse(transacao.substring(29, 37), MASCARA_DATA_RETORNO);
		this.codigoBarras = transacao.substring(37, 81);
		this.valorRecebido = new BigDecimal(Double.valueOf(transacao.substring(81, 93)) / 100);
		this.valorTarifa = new BigDecimal(Double.valueOf(transacao.substring(93, 100)) / 100);
		this.numeroSequencialRegistro = Integer.valueOf(transacao.substring(100, 108));
		this.codigoAgenciaArreacadadora = transacao.substring(108, 116);
		this.formaArrecadacao = transacao.substring(116, 117);
		this.codigoTransacao = transacao.substring(117, 140);
		this.formaParamento = Integer.valueOf(transacao.substring(140, 141));

	}

}
