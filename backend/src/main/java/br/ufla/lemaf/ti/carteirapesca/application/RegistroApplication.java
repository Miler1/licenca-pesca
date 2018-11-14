package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;

/**
 * Serviço de Registro da camada de application.
 * <p>
 * Gerencia o registro de licenças, bem como suas renovações.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface RegistroApplication {

	/**
	 * Verifica se o usuario pode criar uma nova licença,
	 * e em casos positivos cria a mesma.
	 * <p>
	 * Caso o usuário não estiver cadastrado, o cadastra.
	 *
	 * @param resource Os dados de registro
	 * @return O número do protocolo da licença
	 */
	Protocolo registrar(RegistroResource resource);

}