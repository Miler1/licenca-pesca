package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.com.caelum.stella.tinytype.CPF;
import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * Serviço de Acesso Implemnentado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class AcessoApplicationImpl implements AcessoApplication {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa identificar(final AcessoResource acessoResource) {
		// TODO
		throw new NotImplementedException("Serviço não implementado ainda.");
	}

	/**
	 * Confere se dado usuario, representado por seu cpf
	 * possui ou não um cadastro na base de dados.
	 *
	 * @param cpf O CPF do usuário.
	 * @return {@code true} se o usuário existir na
	 * base de dados.
	 */
	private static Boolean existeUsuario(final CPF cpf) {

		validarWebService();

		return CadastroUnificadoService.ws.existeUsuario(cpf.getNumero());

	}

	/**
	 * Busca um usuario do Cadastro Unificado do
	 * Entrada Única.
	 *
	 * @param cpf O CPF do Usuário.
	 * @return O {@link Pessoa}
	 */
	private static Pessoa buscarUsuario(final CPF cpf) {

		validarWebService();

		return CadastroUnificadoService.ws.buscarUsuario(cpf.getNumero());

	}

	/**
	 * Valida o Web Service do Entrada Unica.
	 */
	private static void validarWebService() {

		// Validar Web Service
		Validate.notNull(
			CadastroUnificadoService.ws, "Serviço indisponível."
		);

	}
}
