package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "beneficiario")
public class Beneficiario implements Entity<Beneficiario, Long> {

	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_banco",
		referencedColumnName="id")
	private Banco banco;

	@ManyToOne
	@JoinColumn(name = "id_endereco",
		referencedColumnName="id")
	private EnderecoBeneficiario endereco;

	@Column(name = "codigo_beneficiario")
	private String codigoBeneficiario;

	@Column(name = "digito_codigo_beneficiario")
	private String digitoCodigoBeneficiario;

	private String agencia;

	@Column(name = "digito_agencia")
	private String digitoAgencia;

	private String convenio;

	private String carteira;

	@Column(name = "nosso_numero")
	private String nossoNumero;

	@Column(name = "digito_nosso_numero")
	private String digitoNossoNumero;

	@Override
	public boolean sameIdentityAs(Beneficiario other) {
		return false;
	}

	@Override
	public Long identity() {
		return null;
	}
}
