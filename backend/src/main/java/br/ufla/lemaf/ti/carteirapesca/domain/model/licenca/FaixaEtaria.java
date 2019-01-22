package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;


import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "faixa_etaria")
public class FaixaEtaria {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao_pt")
	private String descricaoPT;

	@Column(name = "descricao_en")
	private String descricaoEN;

	@Column(name = "descricao_es")
	private String descricaoES;
}
