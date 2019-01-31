package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "beneficiario_titulo")
public class BeneficiarioTitulo implements Entity<BeneficiarioTitulo, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String sigla;

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

	@Column(name = "conta_corrente")
	private String contaCorrente;

	@Column(name = "digito_conta_corrente")
	private String digitoContaCorrente;

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
