package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.Getter;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "endereco")
public class Endereco implements Entity<Endereco, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	private String cep;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "estado")
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

	public Endereco(main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco) {

		this.logradouro = endereco.logradouro;
		this.numero = endereco.numero.toString();
		this.complemento = endereco.complemento;
		this.bairro = endereco.bairro;
		this.cep = endereco.cep;
		this.municipio = endereco.municipio.nome;
		this.estado = endereco.municipio.estado.sigla;

	}

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

	public String getDescricaoEndereco() {

		return this.getLogradouro() +
			(this.getNumero() == null ? "" : " Nº " + this.getNumero()) +
			(this.getComplemento() == null ? "" : ", " + this.getComplemento());

	}

	private main.java.br.ufla.lemaf.beans.pessoa.Endereco getEnderecoPessoa(List<main.java.br.ufla.lemaf.beans.pessoa.Endereco> enderecos) {

		return enderecos
			.stream()
			.filter(endereco ->
				StringUtils.isNotBlank(endereco.logradouro) && StringUtils.isNotBlank(endereco.cep))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);
	}

}
