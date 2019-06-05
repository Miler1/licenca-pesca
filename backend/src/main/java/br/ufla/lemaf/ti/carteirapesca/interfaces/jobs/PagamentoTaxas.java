package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import arrecadacao.dtos.DocumentosArrecadacao;
import arrecadacao.services.DocumentoArrecadacaoService;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TaxaLicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class PagamentoTaxas {

	private static final Logger log = LoggerFactory.getLogger(PagamentoTaxas.class);

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	TaxaLicencaRepository taxaLicencaRepository;

	@Scheduled(cron = Constants.JOB_PAGAMENTOS_TAXAS)
	public void ValidaTaxa() {

		log.info(" ----- Verifica taxas pagas - INICIO ----- ");

		buscaDocumentosArrecadacaoPagos();

		log.info(" ----- Verifica taxas pagas - FIM ----- ");

	}

	private void buscaDocumentosArrecadacaoPagos() {

		DocumentosArrecadacao documentosArrecadacao = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
			.listaUltimosDocumentosArrecadacaoPagos();

		Status statusAtivo = statusRepository.findByCodigo(Status.StatusEnum.ATIVO.codigo);

		documentosArrecadacao.content.forEach(d -> {

			TaxaLicenca taxaLicenca = taxaLicencaRepository.findByIdGestaoPagamentos(d.idDocumentoArrecadacao);

			if(taxaLicenca != null) {

				taxaLicenca.setPago(d.pago);
				taxaLicenca.setVencido(false);

				taxaLicenca.getLicenca().setDataAtivacao(new Date());
				taxaLicenca.getLicenca().setDataVencimento(LocalDate.now());
				taxaLicenca.getLicenca().setStatus(statusAtivo);

				taxaLicencaRepository.save(taxaLicenca);

				log.info("Taxa paga licença --> " + taxaLicenca.getLicenca().getProtocolo());

			}

		});

	}

}
