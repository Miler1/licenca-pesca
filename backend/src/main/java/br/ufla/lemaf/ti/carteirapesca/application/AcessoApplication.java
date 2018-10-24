package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.UsuarioDTO;

/**
 * Serviço de Acesso.
 */
public interface AcessoApplication {

	/**
	 * Confere se dado usuario, representado por seu cpf
	 * possui ou não um cadastro na base de dados.
	 *
	 * @param cpf O CPF do usuário.
	 * @return {@code true} se o usuário existir na
	 * base de dados.
	 */
	Boolean existeUsuario(String cpf);

	/**
	 * Busca um usuario do Cadastro Unificado do
	 * Entrada Única.
	 *
	 * @param cpf O CPF do Usuário.
	 * @return O {@link UsuarioDTO}
	 */
	UsuarioDTO buscarUsuario(String cpf);

}
