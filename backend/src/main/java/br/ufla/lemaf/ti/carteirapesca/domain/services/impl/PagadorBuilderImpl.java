package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Endereco;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.PagadorTitulo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.PagadorTituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.PagadorBuilder;
import lombok.extern.slf4j.Slf4j;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PagadorBuilderImpl implements PagadorBuilder {

	@Autowired
	EnderecoBuilderImpl enderecoBuilder;

	@Autowired
	private PagadorTituloRepository pagadorTituloRepository;

	@Override
	public PagadorTitulo transformarPessoaEmPagador(Pessoa pessoa) {

		String cpfPassaporte = (pessoa.cpf == null ? pessoa.passaporte.toString() : pessoa.cpf);

		PagadorTitulo pagadorTitulo = pagadorTituloRepository.findByCpfPassaporte(cpfPassaporte);

		if(pagadorTitulo == null) {

			Endereco endereco = new Endereco(enderecoBuilder.getEnderecoPessoa(pessoa));

			pagadorTitulo = new PagadorTitulo(pessoa.nome, cpfPassaporte, endereco);

			pagadorTituloRepository.save(pagadorTitulo);

		}

		return pagadorTitulo;

	}

}
