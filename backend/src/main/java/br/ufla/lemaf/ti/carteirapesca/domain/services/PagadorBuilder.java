package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.PagadorTitulo;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

public interface PagadorBuilder {

	PagadorTitulo transformarPessoaEmPagador(Pessoa pessoa);

}
