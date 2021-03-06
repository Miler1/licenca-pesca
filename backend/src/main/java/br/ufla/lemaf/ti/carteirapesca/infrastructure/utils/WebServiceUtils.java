package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.webservices.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.BaseException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utils para o Web Service do Entrada Única.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
@UtilityClass
public class WebServiceUtils {

	/**
	 * Valida o Web Service do Entrada Unica.
	 */
	public void validarWebService() {

		if(CadastroUnificadoService.webService() == null) {

			throw new BaseException("entradaUnica.servicoIndisponivel");

		}

	}

	/**
	 * Basicamente um encurtador, para melhor legibilidade.
	 *
	 * @return O web service do EU
	 */
	public CadastroUnificadoService webServiceEU() {
		return CadastroUnificadoService.webService();
	}

}
