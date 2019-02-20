package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

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
	@JoinColumn(name = "id_beneficiario", referencedColumnName="id")
	private PagadorTitulo pagador;

	@ManyToOne
	@JoinColumn(name = "id_beneficiario", referencedColumnName="id")
	private BeneficiarioTitulo beneficiario;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "data_emissao")
	private LocalDate dataEmissao;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "nosso_numero")
	private Integer nossoNumero;

	@Column(name = "codigo_barra")
	private String codigoBarras;

	@Override
	public boolean sameIdentityAs(Convenio other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
