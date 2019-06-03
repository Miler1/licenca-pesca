package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "licenca")
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

	@Column(name = "fl_pago")
	private Boolean pago;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@Override
	public boolean sameIdentityAs(TaxaLicenca other) {
		return false;
	}

	@Override
	public Protocolo identity() {
		return null;
	}
}
