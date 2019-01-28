package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "especie_documento")
public class EspecieDocumento implements Entity<EspecieDocumento, Integer> {

	private Integer id;

	private String codigo;

	private String descricao;

	@Override
	public boolean sameIdentityAs(EspecieDocumento other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
