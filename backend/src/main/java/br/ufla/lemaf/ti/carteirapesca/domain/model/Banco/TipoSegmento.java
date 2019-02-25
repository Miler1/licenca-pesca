package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "tipo_segmento")
public class TipoSegmento implements Entity<TipoSegmento, Integer> {

	@Id
	private Integer id;

	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "descricao")
	private String descricao;

	@Override
	public boolean sameIdentityAs(TipoSegmento other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
