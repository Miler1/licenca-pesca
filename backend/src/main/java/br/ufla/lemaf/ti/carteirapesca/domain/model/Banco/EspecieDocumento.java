package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "especie_documento")
public class EspecieDocumento implements Entity<EspecieDocumento, Integer> {

	@Id
	private Integer id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "codigo_remessa")
	private String codigoRemessa;

	@Column(name = "descricao")
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
