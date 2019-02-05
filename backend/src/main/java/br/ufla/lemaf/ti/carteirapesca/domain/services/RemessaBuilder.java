package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;

import java.io.IOException;

public interface RemessaBuilder {

	Remessa geraRemessa() throws IOException;

}
