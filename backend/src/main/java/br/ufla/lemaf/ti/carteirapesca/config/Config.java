package br.ufla.lemaf.ti.carteirapesca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Classe de configurações globais da aplicação.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class Config implements EnvironmentAware{

	@Autowired
	private static Environment environment;

	public static String APPLICATION_VERSION;

	public static String DATE_FORMAT;
	public static String DATE_FORMAT_TIMETABLE;
	public static String DATE_FORMAT_TIME;

	public static String FRONTEND_URL;
	public static String BACKEND_URL;

	public static String AUTH_SERVICES_PACKAGE;
	public static String AUTH_SERVICE;
	public static String SESSION_KEY_USUARIO_SESSAO;

	public static String ENTRADA_UNICA_CLIENTE_ID;
	public static String ENTRADA_UNICA_CLIENTE_SECRET;
	public static String ENTRADA_UNICA_URL_PORTAL_SEGURANCA;
	public static String ENTRADA_UNICA_URL_CADASTRO_UNIFICADO;

	/**
	 * Inicializa a variável de ambiente
	 * @param environment O Ambiente
	 */
	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	/**
	 * Registro das propriedades globais da aplicação.
	 */
	@Autowired
	public Config() {

		APPLICATION_VERSION = getStringConfig("application.version");

		DATE_FORMAT = getStringConfig("data.formatada.padrao");
		DATE_FORMAT_TIME = getStringConfig("data.formatada.time");
		DATE_FORMAT_TIMETABLE = getStringConfig("data.formatada.timetable");

		FRONTEND_URL = getStringConfig("frontend.url");
		BACKEND_URL = getStringConfig("backend.url");

		AUTH_SERVICES_PACKAGE = getStringConfig("auth.servicePack");
		AUTH_SERVICE = getStringConfig("auth.service");
		SESSION_KEY_USUARIO_SESSAO = getStringConfig("session.keyUsuarioLogado", "usuarioLogado");

		ENTRADA_UNICA_CLIENTE_ID = getStringConfig("", "");
		ENTRADA_UNICA_CLIENTE_SECRET = getStringConfig("", "");
		ENTRADA_UNICA_URL_PORTAL_SEGURANCA = getStringConfig("", "");
		ENTRADA_UNICA_URL_CADASTRO_UNIFICADO = getStringConfig("", "");
	}

	/**
	 * Busca a string de configuração no arquivo de properties
	 *
	 * @param key Chave da configuração
	 * @return Configuração em {@link String}
	 */
	private static String getStringConfig(String key) {

		String config = getStringConfig(key, null);

		if (config != null)
			return config;
		return "";
	}

	/**
	 * Retorna a {@link String} de configuração ou a {@link String} passada como default
	 * no caso do resultado da {@param key} ser nula.
	 *
	 * @param key Chave da configuração
	 * @param defaultValue Valor padrão
	 * @return Configuração em {@link String}
	 */
	private static String getStringConfig(String key, String defaultValue) {

		String config = environment.getRequiredProperty(key);

		return (config != null) ? config : defaultValue;
	}

}
