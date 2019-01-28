package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "pagador_titulo")
public class PagadorTitulo implements Entity<BeneficiarioTitulo, Integer> {

	private static final String SEQUENCIA = Constants.SCHEMA_CARTEIRA_PESCA + ".pagador_titulo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCIA)
	@SequenceGenerator(name = SEQUENCIA,
		sequenceName = SEQUENCIA,
		allocationSize=1)
	private Integer id;

	private String nome;

	@Column(name = "cpf_passaporte")
	private String cpfPassaporte;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco",
		referencedColumnName="id")
	private Endereco endereco;

	@Override
	public boolean sameIdentityAs(BeneficiarioTitulo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

	public PagadorTitulo() {}

	public PagadorTitulo(String nome, String cpfPassaporte) {

		this.nome = nome;
		this.cpfPassaporte = cpfPassaporte;

	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
