package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "tipo_valor_efetivo")
public class TipoValorEfetivo implements Entity<TipoValorEfetivo, Integer> {

	@Id
	private Integer id;

	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "descricao")
	private String descricao;

	@Override
	public boolean sameIdentityAs(TipoValorEfetivo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
