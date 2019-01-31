package br.ufla.lemaf.ti.carteirapesca.application;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Pais;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;

import java.util.List;

/**
 * Serviço de Consulta da camada de application.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ConsultaApplication {

	/**
	 * Busca uma licença.
	 *
	 * @param protocolo O numero do protocolo.
	 * @return A Licença
	 */
	Licenca consulta(Protocolo protocolo);

	/**
	 * Busca a rota do arquivo da carteira.
	 *
	 * @param protocolo O protocolo da licença
	 * @return String com o caminho do arquivo
	 */
	String buscarCaminhoCarteira(Protocolo protocolo);

	/**
	 * Busca a rota do arquivo de boleto.
	 *
	 * @param protocolo O protocolo da licença
	 * @return String com o caminho do arquivo
	 */
	String buscarCaminhoBoleto(Protocolo protocolo);

	List<Pais> fetchPaises();

}
