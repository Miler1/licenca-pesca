package br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "tipo_arquivo")
public class TipoArquivo implements Entity<TipoArquivo, Integer> {

	private static final String SEQUENCIA = Constants.SCHEMA_CARTEIRA_PESCA + ".titulo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCIA)
	@SequenceGenerator(name = SEQUENCIA,
		sequenceName = SEQUENCIA,
		allocationSize=1)
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
