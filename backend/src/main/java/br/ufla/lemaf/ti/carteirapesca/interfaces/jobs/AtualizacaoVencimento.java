package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.RegistroServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoVencimento {

	@Autowired
	private RegistroServiceFacade registroServiceFacade;

	private static final Logger log = LoggerFactory.getLogger(AtualizacaoVencimento.class);

	@Scheduled(cron = Constants.INTERVALO_ATUALIZACAO_AUTOMATICA)
	public void atualizacaoAutomatica() {

		log.info(" ----- INICIO - ATUALIZACAO AUTOMATICA ----- ");

		// Verifica vencimento
		registroServiceFacade.atualizarCondicaoVencimento();

		log.info(" ----- FIM - ATUALIZACAO AUTOMATICA ----- ");

	}
}
