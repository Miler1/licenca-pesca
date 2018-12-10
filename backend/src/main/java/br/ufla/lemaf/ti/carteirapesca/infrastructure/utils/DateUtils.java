package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Uitários para data.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@UtilityClass
public class DateUtils {

	/**
	 * @return O valor do ano em vigência.
	 */
	public int getThisYear() {
		val calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		return calendar.get(Calendar.YEAR);
	}

	/**
	 *
	 * @param data
	 * @param formato
	 * @return
	 */
	public static String formatDate(Date data, String formato) {

		SimpleDateFormat sdf = new SimpleDateFormat(formato);

		return sdf.format(data);

	}


}
