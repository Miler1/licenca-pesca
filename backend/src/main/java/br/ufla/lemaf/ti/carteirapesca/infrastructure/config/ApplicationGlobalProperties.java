package br.ufla.lemaf.ti.carteirapesca.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Propriedades globais de configuração da aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Component
@PropertySource("classpath:application.yml")
public class ApplicationGlobalProperties {

	private Environment environment;

	public static String ENTRADA_UNICA_CLIENTE_ID;
	public static String ENTRADA_UNICA_CLIENTE_SECRET;
	public static String ENTRADA_UNICA_URL_PORTAL_SEGURANCA;
	public static String ENTRADA_UNICA_URL_CADASTRO_UNIFICADO;

	/**
	 * Registro das variáveis de configuração.
	 *
	 * @param environment ambiente
	 */
	@Autowired
	public ApplicationGlobalProperties(Environment environment) {

		this.environment = environment;

		ENTRADA_UNICA_CLIENTE_ID = environment.getProperty(
			"entradaUnica.clienteId"
		);
		ENTRADA_UNICA_CLIENTE_SECRET = environment.getProperty(
			"entradaUnica.clienteSecret"
		);
		ENTRADA_UNICA_URL_PORTAL_SEGURANCA = environment.getProperty(
			"entradaUnica.portalSeguranca"
		);
		ENTRADA_UNICA_URL_CADASTRO_UNIFICADO = environment.getProperty(
			"entradaUnica.cadastroUnificado"
		);

	}
}
