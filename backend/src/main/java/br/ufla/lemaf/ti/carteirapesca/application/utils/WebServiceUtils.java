package br.ufla.lemaf.ti.carteirapesca.application.utils;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

/**
 * Utils para o Web Service do Entrada Única.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@UtilityClass
public class WebServiceUtils {

	/**
	 * Valida o Web Service do Entrada Unica.
	 */
	public void validarWebService() {

		try {
			Validate.notNull(
				CadastroUnificadoService.ws,
				Message.get("entradaUnica.servicoIndisponivel")
			);
		} catch (NullPointerException ex) {

			throw new BaseException("entradaUnica.servicoIndisponivel");

		}

	}

}
