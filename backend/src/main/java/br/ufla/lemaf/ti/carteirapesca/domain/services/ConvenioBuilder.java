package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Convenio;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

import java.io.File;

public interface ConvenioBuilder {

	Convenio geraConvenio(Modalidade modalidade, Pessoa pessoa);

	File geraDocumentoArrecadacao(Convenio convenio) throws Exception;

}
