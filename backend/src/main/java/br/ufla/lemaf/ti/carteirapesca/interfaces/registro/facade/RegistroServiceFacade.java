package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade;

import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ProtocoloDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;

/**
 * Serviço de Facade de Registro da camada de interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface RegistroServiceFacade {


	/**
	 * Facade de registro de licença.
	 * <p>
	 * Converte e encapsula os dados da camada de interface,
	 * bem como valida-os.
	 * <p>
	 * O Serviço {@code #novaLicenca()} do facade da interface de
	 * registro tem como principal objetivo, garantir a integridade
	 * dos dados oriundos da controller e convertê-los na linguagem
	 * em que a camada de application possa entender. Ou seja,
	 * dado o {@link RegistroResource}, valida cada um de seus parâmetros
	 * e ao receber o dado da service de application no formato
	 * {@link br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo} converte-o
	 * em {@link ProtocoloDTO} com o
	 * {@link br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ProtocoloDTOAssembler}.
	 *
	 * @param resource O recurso de registro do usuário.
	 * @return O Protocolo da licença.
	 */
	ProtocoloDTO registrar(RegistroResource resource);

}
