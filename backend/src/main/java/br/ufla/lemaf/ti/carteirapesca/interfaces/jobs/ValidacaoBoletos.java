package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.CondicaoConvenioEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.CondicaoConvenio;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.CondicaoConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.ConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoBoletos {

	static final Logger log = LoggerFactory.getLogger(ValidacaoBoletos.class);

	@Autowired
	ConvenioRepository convenioRepository;

	@Autowired
	CondicaoConvenioRepository condicaoConvenioRepository;

	@Scheduled(cron = Constants.INTERVALO_VALIDACAO_BOLETOS_JOB)
	public void alteraCondicaoVencidos() {

		log.info("\n\n====== INICIO - ALTERA CONDICAO VENCIDOS ======");

		CondicaoConvenio condicaoVencido = condicaoConvenioRepository.findByCodigo(CondicaoConvenioEnum.VENCIDO.getCodigo());
		CondicaoConvenio condicaoAguardandoPagamento = condicaoConvenioRepository.findByCodigo(CondicaoConvenioEnum.AGUARDANDO_PAGAMENTO.getCodigo());
		convenioRepository.alteraCondicaoBoletosVencidos(condicaoVencido, condicaoAguardandoPagamento);

		log.info("====== FIM - ALTERA CONDICAO VENCIDOS ======\n\n");

	}

}
