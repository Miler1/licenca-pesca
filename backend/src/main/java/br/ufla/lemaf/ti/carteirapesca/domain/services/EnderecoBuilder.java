package br.ufla.lemaf.ti.carteirapesca.domain.services;

import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

public interface EnderecoBuilder {

	public Endereco getEnderecoPessoa(Pessoa pessoa);

}
