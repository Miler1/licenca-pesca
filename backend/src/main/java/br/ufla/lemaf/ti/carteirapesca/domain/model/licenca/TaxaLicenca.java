package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import arrecadacao.dtos.RetornoArrecadacaoDTO;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "taxa_licenca")
public class TaxaLicenca implements Entity<TaxaLicenca, Protocolo> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_licenca", referencedColumnName="id")
	private Licenca licenca;

	@Column(name = "id_gestao_pagamento")
	private Integer idGestaoPagamentos;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "codigo_barras")
	private String codigoBarras;

	@Setter
	@Column(name = "fl_pago", insertable = false)
	private Boolean pago;

	@Setter
	@Column(name = "fl_vencido", insertable = false)
	private Boolean vencido;

	@Override
	public boolean sameIdentityAs(TaxaLicenca other) {
		return false;
	}

	@Override
	public Protocolo identity() {
		return null;
	}

	public TaxaLicenca() {}

	public TaxaLicenca(Licenca licenca, RetornoArrecadacaoDTO arrecadacao) {

		this.licenca = licenca;

		this.idGestaoPagamentos = arrecadacao.idDocumentoArrecadacao;
		this.dataVencimento = arrecadacao.dataVecimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.codigoBarras = arrecadacao.codigoBarras;
		this.pago = arrecadacao.pago;
		this.valor = arrecadacao.valor;

	}

}
