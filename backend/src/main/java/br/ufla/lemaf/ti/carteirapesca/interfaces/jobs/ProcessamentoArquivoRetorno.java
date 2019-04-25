package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoConvenioBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessamentoArquivoRetorno {

	private static final Logger log = LoggerFactory.getLogger(ProcessamentoArquivoRetorno.class);

	@Autowired
	private RetornoConvenioBuilder retornoConvenioBuilder;

	@Scheduled(cron = Constants.INTERVALO_PROCESSAMENTO_JOB)
	public void processamentoRetorno() throws Exception {

		log.info("====== INICIO - PROCESSAMENTO DO ARQUIVO DE RETORNO ======");

		retornoConvenioBuilder.buscaArquivoRetornoProcessamento();

		log.info("======= FIM - PROCESSAMENTO DO ARQUIVO DE RETORNO ========");

	}

}
