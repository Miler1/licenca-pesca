package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;

import java.io.IOException;
import java.util.List;

public interface Remessa400Builder {

	String geraRemessa(List<Titulo> titulos) throws IOException;

}
