package br.ufla.lemaf.ti.carteirapesca.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Propriedades globais da aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Component
@EnableAutoConfiguration
public final class Properties {

	private static Environment environment;

	/**
	 * Injetando dependências.
	 *
	 * @param env A variável de ambiente
	 */
	@Autowired
	private Properties(final Environment env) {
		setEnvironment(env);
	}

	/**
	 * Setter de environment. Necessário já que
	 * environment é um atributo estático e a
	 * injeção de dependências que a usa não é
	 * estático.
	 *
	 * @param environment A variável de ambiente.
	 */
	public static void setEnvironment(Environment environment) {
		Properties.environment = environment;
	}

	/**
	 * @return A propriedade do Id do Cliente
	 */
	public static String clientId() {
		return environment.getProperty("spring.security.entradaUnica.client-id");
	}

	/**
	 * @return A propriedade do Secret do Cliente
	 */
	public static String clientSecret() {
		return environment.getProperty("spring.security.entradaUnica.client-secret");
	}

	/**
	 * @return A propriedade do URL do Portal de Segurança
	 */
	public static String portalSegurancaUrl() {
		return environment.getProperty("spring.security.entradaUnica.portal-seguranca.url");
	}

	/**
	 * @return A propriedade do URL do Cadastro Unificado
	 */
	public static String cadastroUnificadoUrl() {
		return environment.getProperty("spring.security.entradaUnica.cadastro-unificado.url");
	}

	/**
	 * @return O caminho da carteira de pesca
	 */
	public static String pathCarteiraPesca() {
		return environment.getProperty("spring.carteira-pesca.base-path");
	}

	/**
	 * @return O caminho da do boleto da licença de pesca
	 */
	public static String pathBoletoPagamentoCarteiraPesca() {
		return environment.getProperty("spring.carteira-pesca.path-boleto");
	}


	public static String baseUrl() {
		return environment.getProperty("spring.application.urlBase");
	}

}
