package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "ocorrencia")
public class Ocorrencia implements Entity<Ocorrencia, Integer> {

	@Id
	private Integer id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "codigo")
	private Integer codigo;

	@Override
	public boolean sameIdentityAs(Ocorrencia other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
