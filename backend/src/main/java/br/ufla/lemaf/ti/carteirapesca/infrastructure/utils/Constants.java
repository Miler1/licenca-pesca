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

	public static final Integer ZONA_URBANA = 0;

	public static final Integer ZONA_RURAL = 1;

	public static final String SCHEMA_CARTEIRA_PESCA = "carteira_pesca";

	public static final Integer BOLETO = 0;

	public static final Integer CARTEIRA = 1;

	public static final Integer MASCULINO = 0;

	public static final Integer FEMININO = 1;

	public static final Integer BRASIL = 29;

	public static  final String DATE_FORMAT = "dd/MM/yyyy";

	public static final Integer NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE = 3;

	public static final String INTERVALO_ATUALIZACAO_AUTOMATICA = " 0 0/1 * 1/1 * ?";

	public static final Integer HORAS_BLOQUEIO_SOLICITANTE = 2;

	/**
	 * Para evitar instanciação.
	 */
	private Constants() {
	}

}
