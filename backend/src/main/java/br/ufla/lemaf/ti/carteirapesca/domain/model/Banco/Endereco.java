package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "endereco")
public class Endereco implements Entity<Endereco, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String municipio;

	private String estado;

	@Override
	public boolean sameIdentityAs(Endereco other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

	public Endereco() {}

	public Endereco(String logradouro,
					String numero,
					String complemento,
					String bairro,
					String cep,
					String municipio,
					String estado) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = municipio;
		this.estado = estado;

	}

}
