package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.com.caelum.stella.tinytype.CPF;
import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Passaporte;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
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
		CPF cpf;
		Passaporte passaporte;

		// Confere se a busca é por CPF ou Passaporte
		if (acessoResource.getCpf() != null) {

			cpf = new CPF(acessoResource.getCpf());

			return buscarPessoa(cpf);

		} else {

			passaporte = new Passaporte(acessoResource.getPassaporte());

			return buscarPessoa(passaporte);

		}

	}

	/**
	 * Checa se existe o usuário, re torna-o caso positivo,
	 * ou um {@link Pessoa} vazio se não existir.
	 *
	 * @param cpf O {@link CPF} do Usuário
	 * @return A {@link Pessoa}
	 */
	private static Pessoa buscarPessoa(final CPF cpf) {

		Pessoa pessoa;

		// Se existir o usuário, o retorna
		// caso contrário, retorna uma pessoa
		// vazia
		if (existeUsuario(cpf)) {

			pessoa = buscarUsuario(cpf);

		} else {

			pessoa = new Pessoa();
			pessoa.cpf = cpf.getNumeroFormatado();

		}

		return pessoa;

	}

	/**
	 * Checa se existe o usuário, re torna-o caso positivo,
	 * ou um {@link Pessoa} vazio se não existir.
	 *
	 * @param passaporte O {@link CPF} do Usuário
	 * @return A {@link Pessoa}
	 */
	private static Pessoa buscarPessoa(final Passaporte passaporte) {

		Pessoa pessoa;

		// Se existir o usuário, o retorna
		// caso contrário, retorna uma pessoa
		// vazia
		if (existeUsuario(passaporte)) {

			pessoa = buscarUsuario(passaporte);

		} else {

			pessoa = new Pessoa();
			pessoa.passaporte = passaporte.getNumero();

		}

		return pessoa;

	}

	/**
	 * Confere se dado usuario, representado por seu cpf
	 * possui ou não um cadastro na base de dados.
	 *
	 * @param cpf O {@link CPF} do usuário.
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
	 * @param cpf O {@link CPF} do Usuário.
	 * @return O {@link Pessoa}
	 */
	private static Pessoa buscarUsuario(final CPF cpf) {

		validarWebService();

		return CadastroUnificadoService.ws.buscarUsuario(cpf.getNumero());

	}

	/**
	 * Confere se dado usuario, representado por seu cpf
	 * possui ou não um cadastro na base de dados.
	 *
	 * @param passaporte O {@link Passaporte} do usuário.
	 * @return {@code true} se o usuário existir na
	 * base de dados.
	 */
	private static Boolean existeUsuario(final Passaporte passaporte) {

		validarWebService();

		return CadastroUnificadoService.ws.existeUsuario(passaporte.getNumero());

	}

	/**
	 * Busca um usuario do Cadastro Unificado do
	 * Entrada Única.
	 *
	 * @param passaporte O {@link Passaporte} do Usuário.
	 * @return O {@link Pessoa}
	 */
	private static Pessoa buscarUsuario(final Passaporte passaporte) {

		validarWebService();

		return CadastroUnificadoService.ws.buscarUsuario(passaporte.getNumero());

	}

	/**
	 * Valida o Web Service do Entrada Unica.
	 */
	private static void validarWebService() {

		// Validar Web Service
		Validate.notNull(
			CadastroUnificadoService.ws,
			"Serviço de Web Service do Entrada Unica indisponível."
		);

	}
}
