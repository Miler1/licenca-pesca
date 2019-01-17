package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import lombok.extern.slf4j.Slf4j;
import main.java.br.ufla.lemaf.beans.pessoa.FiltroPessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		filtroPessoa.isUsuario = false;

		if(acessoResource.getCpf() != null) {
			filtroPessoa.login = acessoResource.getCpf();
		} else {
			filtroPessoa.passaporte = acessoResource.getPassaporte();
		}

		return WebServiceUtils
			.webServiceEU()
			.buscarPessoaComFiltro(filtroPessoa);

	}

}
