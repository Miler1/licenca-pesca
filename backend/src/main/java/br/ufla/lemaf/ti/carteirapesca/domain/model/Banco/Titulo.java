package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "titulo")
public class Titulo implements Entity<Titulo, Long> {

	@ManyToOne
	@JoinColumn(name = "id_beneficiario",
		referencedColumnName="id")
	private Beneficiario beneficiario;

	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;

	@Column(name = "dt_processamento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataProcessamento;

	@Column(name = "dt_vencimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@Column(name = "especie_documento")
	private String especieDocumento;

	private String instrucoes;

	@Column(name = "local_pagamento")
	private String localPagamento;

	@Override
	public boolean sameIdentityAs(Titulo other) {
		return false;
	}

	@Override
	public Long identity() {
		return null;
	}
}
