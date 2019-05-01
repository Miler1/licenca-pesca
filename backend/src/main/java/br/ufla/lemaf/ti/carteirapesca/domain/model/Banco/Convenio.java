package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "convenio")
public class Convenio implements Entity<Convenio, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_segmento", referencedColumnName="id")
	private TipoSegmento tipoSegmento;

	@ManyToOne
	@JoinColumn(name = "id_tipo_valor_efetivo", referencedColumnName="id")
	private TipoValorEfetivo tipoValorEfetivo;

	@ManyToOne
	@JoinColumn(name = "id_pagador", referencedColumnName="id")
	private PagadorTitulo pagador;

	@ManyToOne
	@JoinColumn(name = "id_beneficiario", referencedColumnName="id")
	private Beneficiario beneficiario;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "data_emissao")
	private LocalDate dataEmissao;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Setter
	@Column(name = "nosso_numero")
	private Integer nossoNumero;

	@Setter
	@Column(name = "linha_digitavel")
	private String linhaDigitavel;

	@Setter
	@Column(name = "codigo_barras")
	private String codigoBarras;

	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pagamento", referencedColumnName="id")
	private PagamentoConvenio pagamento;

	@Setter
	@OneToOne
	@JoinColumn(name = "id_condicao", referencedColumnName="id", nullable = false)
	private CondicaoConvenio condicao;

	public Convenio() {}

	public Convenio(TipoSegmento tipoSegmento,
					TipoValorEfetivo tipoValorEfetivo,
					PagadorTitulo pagador,
					Beneficiario beneficiario,
					BigDecimal valor,
					CondicaoConvenio condicaoConvenio) {

		this.tipoSegmento = tipoSegmento;
		this.tipoValorEfetivo = tipoValorEfetivo;
		this.pagador = pagador;
		this.beneficiario = beneficiario;
//		this.valor = valor;
		this.valor = new BigDecimal(0.10);
		this.dataEmissao = LocalDate.now();
		this.setDataVencimento();
		this.condicao = condicaoConvenio;

	}

	public void setNossoNumero(Long qtdCadastrados) {
		this.nossoNumero =  qtdCadastrados.intValue() + 1;
	}

	public void setDataVencimento() {

		Long qtdDiasVencimentoAposEmissao = 3l;

		LocalDate vencimento = LocalDate.now().plusDays(qtdDiasVencimentoAposEmissao);

		if(vencimento.getDayOfWeek() == DayOfWeek.SATURDAY) {
			vencimento = vencimento.plusDays(2);
		} else if(vencimento.getDayOfWeek() == DayOfWeek.SATURDAY) {
			vencimento = vencimento.plusDays(1);
		}

		this.dataVencimento = vencimento;

	}

	@Override
	public boolean sameIdentityAs(Convenio other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
