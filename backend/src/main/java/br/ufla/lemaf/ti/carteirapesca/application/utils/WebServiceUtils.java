package br.ufla.lemaf.ti.carteirapesca.application.utils;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

/**
 * Utils para o Web Service do Entrada Única.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
public final class WebServiceUtils {

	/**
	 * Valida o Web Service do Entrada Unica.
	 */
	public static void validarWebService() {
		log.info("Validando Web service do Entrada Unica.");

		// Validar Web Service
		Validate.notNull(
			CadastroUnificadoService.ws,
			"Serviço de Web Service do Entrada Unica indisponível."
		);

	}

	/**
	 * Construtor vazio para impedir
	 * instanciação.
	 */
	@SuppressWarnings("unused")
	private WebServiceUtils() {
	}
}
