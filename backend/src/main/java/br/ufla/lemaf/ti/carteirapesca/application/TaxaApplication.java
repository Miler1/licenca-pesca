package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;

import java.io.File;
import java.io.IOException;

public interface TaxaApplication {

	TaxaLicenca geraDocumentoArrecadacao(Licenca licenca);

	File downloadDocumentoArrecadacao(Licenca licenca) throws IOException;

}
