package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "titulo")
public class Titulo implements Entity<Titulo, Integer> {

	private static final Integer QTD_MESES_VENCIMENTO_TITULO_APOS_EMISSAO = 1;

	private static final String SEQUENCIA = Constants.SCHEMA_CARTEIRA_PESCA + ".titulo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCIA)
	@SequenceGenerator(name = SEQUENCIA,
		sequenceName = SEQUENCIA,
		allocationSize=1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_beneficiario",
		referencedColumnName="id")
	private BeneficiarioTitulo beneficiario;

	@ManyToOne
	@JoinColumn(name = "id_pagador",
		referencedColumnName="id")
	private PagadorTitulo pagador;

	private BigDecimal valor;

	@Column(name = "dt_criacao")
	private LocalDate dataCriacao;

	@Column(name = "dt_processamento")
	private LocalDate dataProcessamento;

	@Column(name = "dt_vencimento")
	private LocalDate dataVencimento;

	@ManyToOne
	@JoinColumn(name = "id_especie_documento",
		referencedColumnName="id")
	private EspecieDocumento especieDocumento;

	@Column(name = "instrucoes")
	private String instrucoes;

	@Column(name = "local_pagamento")
	private String localPagamento;

	@Column(name = "fl_enviado_banco", insertable = false)
	private Boolean enviadoBanco;

	@Column(name = "nosso_numero")
	private String nossoNumero;

	@Override
	public boolean sameIdentityAs(Titulo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

	public Titulo() {}

	public Titulo(BeneficiarioTitulo beneficiario,
				  EspecieDocumento especieDocumento,
				  PagadorTitulo pagador,
				  BigDecimal valor) {

		this.beneficiario = beneficiario;
		this.pagador = pagador;

		this.valor = valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.dataCriacao = LocalDate.now();
		this.dataProcessamento = LocalDate.now();
		this.dataVencimento = LocalDate.now().plusMonths(QTD_MESES_VENCIMENTO_TITULO_APOS_EMISSAO);
		this.especieDocumento = especieDocumento;
		this.nossoNumero = String.format("%1$11s", "");
		this.setInstrucoes();
		this.setLocalPagamento();

	}

	private void setInstrucoes() {
		this.instrucoes = "Sr(a). Caixa: Não aceitar pagamento após vencimento";
	}

	private void setLocalPagamento() {
		this.localPagamento = "Pagável em qualquer banco até o vencimento.";
	}

}
