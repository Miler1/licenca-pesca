package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

public interface BoletoBuilder {

	Titulo gerarBoleto(Protocolo protocolo, Modalidade modalidade, Pessoa pessoa);

}
