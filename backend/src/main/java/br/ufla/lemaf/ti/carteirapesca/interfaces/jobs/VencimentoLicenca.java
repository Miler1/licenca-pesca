package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.RegistroServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VencimentoLicenca {

	@Autowired
	private RegistroServiceFacade registroServiceFacade;

	private static final Logger log = LoggerFactory.getLogger(VencimentoLicenca.class);

	@Scheduled(cron = Constants.JOB_VENCIMENTO_LICENCA)
	public void vencimentoLicenca() {

		log.info(" ----- Verifica validade licenças - INICIO ----- ");

		registroServiceFacade.atualizarCondicaoVencimento();

		log.info(" ----- Verifica validade licenças - FIM ----- ");

	}
}
