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
public class Config {

	private Environment env;

	public static String ENTRADA_UNICA_CLIENTE_ID;
	public static String ENTRADA_UNICA_CLIENTE_SECRET;
	public static String ENTRADA_UNICA_URL_PORTAL_SEGURANCA;
	public static String ENTRADA_UNICA_URL_CADASTRO_UNIFICADO;
	public static String DATE_FORMAT;
	public static String DATE_FORMAT_TIMETABLE;
	public static String DATE_FORMAT_TIME;
	public static String DATE_FORMAT_CAR;


	/**
	 * Registro das variáveis de configuração.
	 *
	 * @param env ambiente
	 */
	@Autowired
	public Config(Environment env) {

		this.env = env;

		ENTRADA_UNICA_CLIENTE_ID = env.getProperty("entradaUnica.clienteId");
		ENTRADA_UNICA_CLIENTE_SECRET = env.getProperty("entradaUnica.clienteSecret");
		ENTRADA_UNICA_URL_PORTAL_SEGURANCA = env.getProperty("entradaUnica.portalSeguranca");
		ENTRADA_UNICA_URL_CADASTRO_UNIFICADO = env.getProperty("entradaUnica.cadastroUnificado");
		DATE_FORMAT = env.getProperty("spring.application.date-format");
		DATE_FORMAT_TIMETABLE = env.getProperty("spring.application.date-format-timetable");
		DATE_FORMAT_TIME = env.getProperty("spring.application.date-format-time");
		DATE_FORMAT_CAR = env.getProperty("spring.application.date-format-car");


	}
}
