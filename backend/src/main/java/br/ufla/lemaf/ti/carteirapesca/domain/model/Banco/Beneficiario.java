package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;


import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "beneficiario")
public class Beneficiario implements Entity<Beneficiario, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla")
	private String sigla;

	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", referencedColumnName="id")
	private Endereco endereco;

	@Override
	public boolean sameIdentityAs(Beneficiario other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

}
