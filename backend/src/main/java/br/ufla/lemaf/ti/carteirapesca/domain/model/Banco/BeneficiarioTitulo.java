package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "beneficiario_titulo")
public class BeneficiarioTitulo implements Entity<BeneficiarioTitulo, Integer> {

	private static final String SEQUENCIA = Constants.SCHEMA_CARTEIRA_PESCA + ".beneficiario_titulo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCIA)
	@SequenceGenerator(name = SEQUENCIA,
		sequenceName = SEQUENCIA,
		allocationSize=1)
	private Integer id;

	private String nome;

	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@ManyToOne
	@JoinColumn(name = "id_banco",
		referencedColumnName="id")
	private Banco banco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco",
		referencedColumnName="id")
	private Endereco endereco;

	@Column(name = "codigo_beneficiario")
	private String codigoBeneficiario;

	@Column(name = "digito_codigo_beneficiario")
	private String digitoCodigoBeneficiario;

	private String agencia;

	@Column(name = "digito_agencia")
	private String digitoAgencia;

	private String convenio;

	private String carteira;

	@Column(name = "fl_ativo")
	private Boolean ativo;

	@Override
	public boolean sameIdentityAs(BeneficiarioTitulo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
