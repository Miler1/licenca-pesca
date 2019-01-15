package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.CPF;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Passaporte;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.webservices.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import main.java.br.ufla.lemaf.beans.PessoaFiltroResult;
import main.java.br.ufla.lemaf.beans.pessoa.FiltroPessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Serviço de Acesso Implemnentado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class AcessoApplicationImpl implements AcessoApplication {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa identificar(final AcessoResource acessoResource) {

		FiltroPessoa filtroPessoa = new FiltroPessoa();

		if(acessoResource.getCpf() != null) {
			filtroPessoa.login = acessoResource.getCpf();
		} else {
			filtroPessoa.passaporte = acessoResource.getPassaporte();
		}

		return buscarPessoaComFiltro(filtroPessoa);

	}

	public static Pessoa buscarPessoaComFiltro(FiltroPessoa filtro) {

		PessoaFiltroResult pessoas = WebServiceUtils
			.webServiceEU()
			.buscarPessoasComFiltroAll(filtro);

		Pessoa pessoa;

		if(pessoas.pageItems == null || pessoas.pageItems.size() == 0) {

			pessoa = new Pessoa();
			pessoa.cpf = filtro.login;
			pessoa.passaporte = filtro.passaporte;

		} else {
			pessoa = pessoas.pageItems.get(0);
		}

		return pessoa;

	}
}
