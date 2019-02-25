package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "pagamento_convenio")
public class PagamentoConvenio implements Entity<PagamentoConvenio, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@Column(name = "data_credito")
	private LocalDate dataCredito;

	@Column(name = "valor_recebido")
	private BigDecimal valorRecebido;

	@Column(name = "valor_tarifa")
	private BigDecimal valorTarifa;

	@ManyToOne
	@JoinColumn(name = "id_tipo_pagamento", referencedColumnName="id")
	private TipoPagamento tipoPagamento;

	@ManyToOne
	@JoinColumn(name = "id_tipo_arrecadacao", referencedColumnName="id")
	private TipoArrecadacao tipoArrecadacao;

	@Override
	public boolean sameIdentityAs(PagamentoConvenio other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

}
