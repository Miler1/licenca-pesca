package br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "tipo_arquivo")
public class TipoArquivo implements Entity<TipoArquivo, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String codigo;

	private String descricao;

	@Override
	public boolean sameIdentityAs(TipoArquivo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
