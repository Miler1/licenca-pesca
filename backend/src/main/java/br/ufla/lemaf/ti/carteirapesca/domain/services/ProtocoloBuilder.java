package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;

/**
 * Interface do serviço de gerar o protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ProtocoloBuilder {

	/**
	 * Gerador de protocolo. Cria o protocolo
	 * seguindo o modelo em regra:
	 * <p>
	 * <b>LPX-9999/AA</b>
	 * <ul>
	 *   <li>
	 *     <i>LPX: </i>Prefixo que informa a modalidade.
	 *     <i>(Licença Pesca X, no qual X pode ser E, esportiva, ou R, recreativa)</i>
	 *   </li>
	 *   <li><i>9999: </i>A sequência da modalidade, sendo resetada a cada ano.</li>
	 *   <li><i>AA: </i>Os ultimos dois digitos do ano.</li>
	 * </ul>
	 *
	 * @param modalidade A modalidade da licença
	 * @return O Protocolo
	 */
	String gerarProtocolo(Modalidade modalidade);
}
