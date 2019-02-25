package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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
	@Column(name = "codigo_barra")
	private String codigoBarras;

	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName="id")
	private Arquivo documentoArrecadacao;

	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pagamento", referencedColumnName="id")
	private PagamentoConvenio pagamento;

	public Convenio() {}

	public Convenio(TipoSegmento tipoSegmento,
					TipoValorEfetivo tipoValorEfetivo,
					PagadorTitulo pagador,
					Beneficiario beneficiario,
					BigDecimal valor) {

		this.tipoSegmento = tipoSegmento;
		this.tipoValorEfetivo = tipoValorEfetivo;
		this.pagador = pagador;
		this.beneficiario = beneficiario;
		this.valor = valor;
		this.dataEmissao = LocalDate.now();
		this.dataVencimento = LocalDate.of(this.dataEmissao.getYear(), 12, 31);

	}

	public void setNossoNumero(Long qtdCadastrados) {
		this.nossoNumero =  qtdCadastrados.intValue() + 1;
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
