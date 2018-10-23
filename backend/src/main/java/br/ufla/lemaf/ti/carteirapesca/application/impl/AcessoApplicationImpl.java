package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.cadastro.facade.dto.pessoa.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;

/**
 * Serviço de Acesso Implemnentado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
public class AcessoApplicationImpl implements AcessoApplication {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean existeUsuario(final String cpf) {
		log.info("Buscando usuário.");
		Validate.notNull(
			CadastroUnificadoService.ws,
			"Serviço indisponível."
		);
		Validate.notNull(cpf, "CPF é obrigatório.");

		return CadastroUnificadoService.ws.existeUsuario(cpf);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioDTO buscarUsuario(final String cpf) {
		// TODO
		throw new NotImplementedException("Não implementado ainda!");
	}
}
