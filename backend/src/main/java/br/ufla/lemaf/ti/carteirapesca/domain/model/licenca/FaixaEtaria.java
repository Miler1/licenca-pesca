package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;


import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "faixa_etaria")
public class FaixaEtaria implements Serializable {
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "descricao_pt")
	private String descricaoPT;

	@Column(name = "descricao_en")
	private String descricaoEN;

	@Column(name = "descricao_es")
	private String descricaoES;
}
