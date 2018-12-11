package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade;

import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;

/**
 * Serviço de Facade de Consulta da camada de interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ConsultaServiceFacade {

	/**
	 * Busca uma licença dado seu protocolo.
	 *
	 * @param protocolo String com o número de protocolo.
	 * @return A licença.
	 */
	LicencaDTO consultar(String protocolo);

	/**
	 * Busca as rotas dos arquivos.
	 *
	 * @param protocolo O número do protocolo
	 * @param tipo O tipo do arquivo
	 * @return O caminho do arquivo
	 */
	String buscarCaminho(String protocolo, Integer tipo);

}
