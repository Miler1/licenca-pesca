package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "informacao_complementar")
public class InformacaoComplementar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fl_agencia_turismo")
	private Boolean agenciaTurismo;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_faixa_etaria", referencedColumnName = "id")
	private FaixaEtaria faixaEtaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_preferencia_local_pesca", referencedColumnName = "id")
	private PreferenciaLocalPesca localPesca;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_modalidade", referencedColumnName = "id")
	private Modalidade modalidadePesca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_modalidade_mais_praticada", referencedColumnName = "id")
	private Modalidade modalidadeMaisPraticada;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_renda_mensal", referencedColumnName = "id")
	private RendaMensal rendaMensal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_isca", referencedColumnName = "id")
	private TipoIsca tipoIsca;

	@Column(name = "nu_dia_ano")
	private Integer diasPescaPorAno;

	@Column(name="nu_gasto_medio")
	private Double gastoMedioPesca;

	@OneToOne(mappedBy = "informacaoComplementar")
	private Licenca licenca;

}
