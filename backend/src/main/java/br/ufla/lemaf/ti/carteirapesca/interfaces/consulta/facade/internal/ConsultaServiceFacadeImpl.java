package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloFormatter;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTOAssembler;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Facade do serviço de Consulta  implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class ConsultaServiceFacadeImpl implements ConsultaServiceFacade {

	private ConsultaApplication application;

	/**
	 * Injetando dependências.
	 *
	 * @param application A aplicação de consulta
	 */
	@Autowired
	public ConsultaServiceFacadeImpl(ConsultaApplication application) {
		this.application = application;
	}

	/**
	 * Busca uma licença dado seu protocolo.
	 *
	 * @param protocolo String com o número de protocolo.
	 * @return A licença.
	 */
	@Override
	public LicencaDTO consultar(final String protocolo) {

		var formatter = new ProtocoloFormatter();

		var protocoloValido = formatter.isFormatted(protocolo)
			? ProtocoloUtils.unformat(protocolo)
			: protocolo;

		var assembler = new LicencaDTOAssembler();

		return assembler.toDTO(
			application.consulta(new Protocolo(protocoloValido))
		);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminho(final String protocolo, final Integer tipo) {
		var formatter = new ProtocoloFormatter();

		var protocoloValido = new Protocolo(
			formatter.isFormatted(protocolo)
				? ProtocoloUtils.unformat(protocolo)
				: protocolo
		);

		if (tipo.equals(Constants.BOLETO)) {

			return application.buscarCaminhoBoleto(protocoloValido);

		} else if (tipo.equals(Constants.CARTEIRA)) {

			return application.buscarCaminhoCarteira(protocoloValido);

		} else {

			return null;

		}

	}

}
