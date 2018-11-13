package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

/**
 * Constantes da Aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class Constants {

	public static final Integer EMAIL = 1;

	public static final Integer ENDERECO_PRINCIPAL = 1;

	public static final Integer ENDERECO_CORRESPONDENCIA = 2;

	public static final Integer ZONA_RURAL = 2;

	public static final long FIRST_MILLISECONDS_DATE = 0L;

	public static final String DATE_FORMAT = "(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z";

	public static final String DATE_CAR_FORMAT = "(\\d{2})/(\\d{2})/(\\d{4}) (\\d{2}):(\\d{2}):(\\d{2})";

	public static final String TIME_FORMAT = "(\\d{2}):(\\d{2})";

	public static final Integer TIME_LENGTH = 10;


	/**
	 * Para evitar instanciação.
	 */
	private Constants() {
	}

}
