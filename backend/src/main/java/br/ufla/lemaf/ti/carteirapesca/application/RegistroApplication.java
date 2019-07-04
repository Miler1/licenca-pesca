package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

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
	Protocolo registrar(RegistroResource resource) throws Exception;

	Modalidade gerarModalidade(Integer tipo);

	Pessoa buscarDadosSolicitante(Solicitante solicitante);

	Protocolo renovarLicenca(RegistroResource resource, String protocolo) throws Exception;

}
