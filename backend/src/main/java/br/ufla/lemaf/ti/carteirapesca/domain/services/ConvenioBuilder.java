package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import com.lowagie.text.DocumentException;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

import java.io.IOException;

public interface ConvenioBuilder {

	Titulo gerarDocumentoArrecadacao(Protocolo protocolo, Modalidade modalidade, Pessoa pessoa) throws IOException, DocumentException;

}
