package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "tipo_arrecadacao")
public class TipoArrecadacao implements Entity<TipoArrecadacao, Integer> {

	@Id
	private Integer id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descricao")
	private String descricao;

	@Override
	public boolean sameIdentityAs(TipoArrecadacao other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
