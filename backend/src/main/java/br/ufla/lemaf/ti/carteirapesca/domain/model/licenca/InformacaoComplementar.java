package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Table;

@Getter
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "licenca")
public class InformacaoComplementar {
}
