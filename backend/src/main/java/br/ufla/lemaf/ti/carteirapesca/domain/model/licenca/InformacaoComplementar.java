package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "informacao_complementar")
public class InformacaoComplementar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fl_agencia_turismo")
	private Boolean agenciaTurismo;


	@ManyToOne
	@JoinColumn(name = "id_faixa_etaria", referencedColumnName = "id")
	private FaixaEtaria faixaEtaria;

	@ManyToOne
	@JoinColumn(name = "id_preferencia_local_pesca", referencedColumnName = "id")
	private PreferenciaLocalPesca localizacaoPreferencialPesca;

	@ManyToOne
	@JoinColumn(name = "id_local_pesca", referencedColumnName = "id")
	private LocalPesca localPesca;

	@ManyToOne
	@JoinColumn(name = "id_modalidade", referencedColumnName = "id")
	private Modalidade modalidadePesca;

	@ManyToOne
	@JoinColumn(name = "id_material_pesca", referencedColumnName = "id")
	private MaterialPesca materialPesca;

	@ManyToOne
	@JoinColumn(name = "id_renda_mensal", referencedColumnName = "id")
	private RendaMensal rendaMensal;

	@ManyToOne
	@JoinColumn(name = "id_tipo_isca", referencedColumnName = "id")
	private TipoIsca tipoIsca;

	@Column(name = "nu_dia_ano")
	private Integer diasPescaPorAno;

	@Column(name="nu_gasto_medio")
	private Double gastoMedioPesca;

	@OneToOne(mappedBy = "informacaoComplementar")
	private Licenca licenca;


	public Integer getId() {
		return id;
	}

	public Boolean getAgenciaTurismo() {
		return agenciaTurismo;
	}

	public FaixaEtaria getFaixaEtaria() {
		return faixaEtaria;
	}

	public LocalPesca getLocalPesca() {
		return localPesca;
	}

	public Modalidade getModalidadePesca() {
		return modalidadePesca;
	}

	public RendaMensal getRendaMensal() {
		return rendaMensal;
	}

	public TipoIsca getTipoIsca() {
		return tipoIsca;
	}

	public Integer getDiasPescaPorAno() {
		return diasPescaPorAno;
	}

	public Double getGastoMedioPesca() {
		return gastoMedioPesca;
	}

	public PreferenciaLocalPesca getLocalizacaoPreferencialPesca() {
		return localizacaoPreferencialPesca;
	}

	public MaterialPesca getMaterialPesca() {
		return materialPesca;
	}
}
