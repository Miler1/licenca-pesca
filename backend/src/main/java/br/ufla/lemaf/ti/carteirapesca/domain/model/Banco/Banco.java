package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "banco")
public class Banco implements Entity<Banco, Long> {

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "nome")
	private String nome;

	@Override
	public boolean sameIdentityAs(Banco other) {
		return false;
	}

	@Override
	public Long identity() {
		return null;
	}
}
