package br.ufla.lemaf.ti.carteirapesca.interfaces.jobs;

import arrecadacao.dtos.RetornoArrecadacaoDTO;
import arrecadacao.enuns.CondicaoArrecadacaoEnum;
import arrecadacao.services.DocumentoArrecadacaoService;
import br.ufla.lemaf.ti.carteirapesca.application.TaxaApplication;
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
import java.util.List;

@Component
public class ValidadeTaxas {

	private static final Logger log = LoggerFactory.getLogger(ValidadeTaxas.class);

	@Autowired
	TaxaApplication taxaApplication;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	TaxaLicencaRepository taxaLicencaRepository;

	@Scheduled(cron = Constants.JOB_VALIDADE_TAXAS)
	public void verificaValidade() {

		log.info(" ----- Verifica validade taxas - INICIO ----- ");

		verificaValidadeTaxas();

		log.info(" ----- Verifica validade taxas - FIM ----- ");


	}

	private void verificaValidadeTaxas() {

		List<TaxaLicenca> taxas = taxaLicencaRepository.findByDataVencimentoLessThanAndPagoAndVencido(LocalDate.now(), false, false);

		Status statusInvalidado = statusRepository.findByCodigo(Status.StatusEnum.INVALIDADO.codigo);

		taxas.forEach(t -> {

			RetornoArrecadacaoDTO retorno = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
				.buscaDocumentoArrecadacaoPorId(t.getIdGestaoPagamentos());

			if(retorno != null
				&& (retorno.condicao.codigo.equals(CondicaoArrecadacaoEnum.VENCIDO.codigo)
					|| retorno.condicao.codigo.equals(CondicaoArrecadacaoEnum.VENCIDO_AGUARDANDO_PAGAMENTO.codigo))) {

				log.info(" Licença invalidada --> " + t.getLicenca().getProtocolo());

				t.setVencido(true);
				t.getLicenca().setStatus(statusInvalidado);
				taxaLicencaRepository.save(t);

			}

		});

	}

}
