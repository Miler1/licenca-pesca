package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;

import java.util.List;

public interface Remessa240Builder {

	String geraRemessa(List<Titulo> titulos);

}
