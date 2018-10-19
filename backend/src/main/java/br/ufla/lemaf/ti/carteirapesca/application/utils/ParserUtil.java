package br.ufla.lemaf.ti.carteirapesca.application.utils;

import br.ufla.lemaf.ti.carteirapesca.config.Config;
import br.ufla.lemaf.ti.carteirapesca.interfaces.common.Mensagem;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Utilitário para tratar conversões de tipos.
 *
 * @author Highlander
 * @since 0.1
 */
public class ParserUtil {

	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Config.DATE_FORMAT);
	private static SimpleDateFormat DATE_FORMATTER_TIMETABLE = new SimpleDateFormat(Config.DATE_FORMAT_TIMETABLE);
	private static SimpleDateFormat DATE_FORMATTER_TIME = new SimpleDateFormat(Config.DATE_FORMAT_TIME);
	private static SimpleDateFormat DATE_FORMATTER_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public static final long FIRST_MILLISECONDS_DATE = 0L;


	/**
	 * Converte uma {@link String} no formato <b>dd/MM/yyyy</b>
	 * em um tipo {@link Date}.
	 *
	 * @param s {@link String} a ser convertida em {@link Date}.
	 * @return Data em {@link Date}.
	 */
	public static Date parseDate(String s) {

		try {

			return DATE_FORMATTER.parse(s);

		} catch(ParseException e) {

			return handleDateParseError(e);

		}

	}

	/**
	 * Converte uma {@link String} no formato <b>yyyy-MM-dd HH:mm:ss</b>
	 * em um tipo {@link Date}.
	 *
	 * @param s {@link String} a ser convertida em {@link Date}.
	 * @return Data em {@link Date}.
	 */
	public static Date parseDateTimetable(String s) {

		try {

			return DATE_FORMATTER_TIMETABLE.parse(s);

		} catch(ParseException e) {

			return handleDateParseError(e);

		}

	}

	/**
	 * Converte uma {@link String} no formato <b>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</b>
	 * em um tipo {@link Date}.
	 *
	 * @param s {@link String} a ser convertida em {@link Date}
	 * @return Data em {@link Date}.
	 */
	public static Date parseDateISO(String s) {

		try {

			return DATE_FORMATTER_ISO.parse(s);

		} catch(ParseException e) {

			return handleDateParseError(e);

		}

	}

	/**
	 * Converte uma {@link String} no formato de <b>hora</b>
	 * em um tipo {@link Date}.
	 *
	 * @param s {@link String} a ser convertida em {@link Date}.
	 * @return Data em {@link Date}.
	 */
	public static Date parseTimeOnly(String s) {

		return setHours(
				new Date(FIRST_MILLISECONDS_DATE),
				Integer.parseInt(s.split(":")[0]),
				Integer.parseInt(s.split(":")[1]),
				0,
				0
		);

	}

	/**
	 * Converte data em {@link String} seguindo o
	 * formato <b>dd/MM/yyyy</b>.
	 *
	 * @param d {@link Date} a ser convertida em {@link String}.
	 * @return Data em {@link String}.
	 */
	public static String format(Date d) {

		return d != null ? DATE_FORMATTER.format(d) : null;

	}

	/**
	 * Converte data em {@link String} seguindo o
	 * formato <b>yyyy-MM-dd HH:mm:ss</b>.
	 *
	 * @param d {@link Date} a ser convertida em {@link String}.
	 * @return Data em {@link String}.
	 */
	public static String formatTimetable(Date d) {

		return DATE_FORMATTER_TIMETABLE.format(d);

	}

	/**
	 * Converte data em {@link String} seguindo o
	 * formato <b>HH:mm:ss</b>.
	 *
	 * @param d {@link Date} a ser convertida em {@link String}.
	 * @return Data em {@link String}.
	 */
	public static String formatTime(Date d) {

		return DATE_FORMATTER_TIME.format(d);

	}

	/**
	 * Constroi uma {@link Mensagem} de Usuário dado um erro.
	 *
	 * @param errors {@link Set} de errors
	 * @param ovalErrors {@link List} de {@link ConstraintViolation}.
	 * @return {@link String} da {@link Mensagem} de erro.
	 */
	public static String buildUserErrorsMessage(Set<ConstraintViolation<Object>> errors, List<net.sf.oval.ConstraintViolation> ovalErrors) {

		StringBuilder sb = new StringBuilder();

		if(errors != null && !errors.isEmpty()) {

			errors.forEach(e -> sb.append(Mensagem.get(e.getMessage())).append("\n"));

		}

		if(ovalErrors != null && !ovalErrors.isEmpty()) {

			ovalErrors.forEach(e -> sb.append(Mensagem.get(e.getMessage())).append("\n"));

		}

		return sb.length() > 0 ? sb.toString() : null;

	}

	/**
	 * Constroi uma mensagem de LOG.
	 *
	 * @param userErrors {@link String} de errors.
	 * @return {@link String} mensagem de LOG.
	 */
	public static String buildLogErrorsMessage(String userErrors) {

		if(userErrors == null || userErrors.isEmpty()) {

			return null;

		}

		return userErrors.replaceAll("\n", " ");

	}

	/**
	 * Aciona o tratamento de erro para os parses de {@link Date}.
	 *
	 * @param e Erro de Parse.
	 * @return {@code null}.
	 */
	private static Date handleDateParseError(ParseException e) {

		e.printStackTrace();

		return null;

	}

	/**
	 * Gera um {@link Date} dado os parâmetros.
	 *
	 * @param date {@link Date}.
	 * @param horas Horas.
	 * @param minutos Minutos.
	 * @param segundos Segundos.
	 * @param milisegundos Milisegundos.
	 * @return {@link Date}
	 */
	private static Date setHours(Date date, int horas, int minutos, int segundos, int milisegundos) {

		Date data = DateUtils.setHours(date, horas);
		data = DateUtils.setMinutes(data, minutos);
		data = DateUtils.setSeconds(data, segundos);
		data = DateUtils.setMilliseconds(data, milisegundos);

		return data;

	}

}
