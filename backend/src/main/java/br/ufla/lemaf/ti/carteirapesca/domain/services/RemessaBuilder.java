package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface RemessaBuilder {

	Remessa geraRemessa() throws IOException;

	Arquivo getArquivoRemessa(Integer idRemessa);

	Page<Remessa> listaRemessas(Pageable pageable);

}
