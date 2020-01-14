package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.beans.pessoa.Endereco;
import br.ufla.lemaf.beans.pessoa.Pessoa;

public interface EnderecoBuilder {

	public Endereco getEnderecoPessoa(Pessoa pessoa);

}
