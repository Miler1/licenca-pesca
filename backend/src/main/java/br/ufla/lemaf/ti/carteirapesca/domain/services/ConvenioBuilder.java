package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Convenio;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

public interface ConvenioBuilder {

	Convenio geraDocumentoArrecadacao(Protocolo protocolo, Modalidade modalidade, Pessoa pessoa) throws Exception;

}
