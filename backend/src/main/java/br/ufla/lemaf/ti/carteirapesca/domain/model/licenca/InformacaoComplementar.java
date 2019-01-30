package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "informacao_complementar")
public class InformacaoComplementar {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Column(name = "fl_agencia_turismo")
	private Boolean agenciaTurismo;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_faixa_etaria", referencedColumnName = "id")
	private FaixaEtaria faixaEtaria;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_preferencia_local_pesca", referencedColumnName = "id")
	private PreferenciaLocalPesca localizacaoPreferencialPesca;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_local_pesca", referencedColumnName = "id")
	private LocalPesca localPesca;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_modalidade", referencedColumnName = "id")
	private Modalidade modalidadePesca;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_material_pesca", referencedColumnName = "id")
	private MaterialPesca materialPesca;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_renda_mensal", referencedColumnName = "id")
	private RendaMensal rendaMensal;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_tipo_isca", referencedColumnName = "id")
	private TipoIsca tipoIsca;

	@Getter
	@ManyToOne
	@JoinColumn(name = "id_peixe_mais_pescado", referencedColumnName = "id")
	private PeixeMaisPescado peixeMaisPescado;

	@Getter
	@Column(name = "nu_dia_ano")
	private Integer diasPescaPorAno;

	@Getter
	@Column(name="nu_gasto_medio")
	private Double gastoMedioPesca;


	@OneToOne(mappedBy = "informacaoComplementar")
	private Licenca licenca;

}
