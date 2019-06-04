package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import br.ufla.lemaf.ti.carteirapesca.application.TaxaApplication;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VerificaValidadeEPagamentoTaxas {

	private static final Logger log = LoggerFactory.getLogger(VerificaValidadeEPagamentoTaxas.class);

	@Autowired
	TaxaApplication taxaApplication;

	@Scheduled(cron = Constants.INTERVALO_VERIFICACAO_PAGAMENTOS)
	public void ValidaTaxa() {

		log.info(" ----- Verifica taxas pagas - INICIO ----- ");

		taxaApplication.buscaDocumentosArrecadacaoPagos();

		log.info(" ----- Verifica taxas pagas - FIM ----- ");

		log.info(" ----- Verifica validade taxas - INICIO ----- ");

		taxaApplication.verificaValidadeTaxas();

		log.info(" ----- Verifica validade taxas - FIM ----- ");


	}

}
