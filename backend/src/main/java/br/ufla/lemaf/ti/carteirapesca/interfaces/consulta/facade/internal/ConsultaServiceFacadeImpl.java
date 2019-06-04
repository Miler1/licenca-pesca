package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TaxaLicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.CarteiraBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.*;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTOAssembler;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * Facade do serviço de Consulta  implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class ConsultaServiceFacadeImpl implements ConsultaServiceFacade {

	private ConsultaApplication application;

	@Autowired
	PdfGeneratorUtil pdfGenaratorUtil;

	@Autowired
	TaxaLicencaRepository taxaLicencaRepository;

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
	public Licenca consultar(final String protocolo) {

		String protocoloValido;
		if(protocolo.length() == 9) {

			var formatter = new ProtocoloFormatter();

			protocoloValido = formatter.isFormatted(protocolo)
				? ProtocoloUtils.unformat(protocolo)
				: protocolo;

			return application.consulta(new Protocolo(protocoloValido));
		}

		var formatterNovo = new ProtocoloFormatter("$1-$2/$3-$4", ProtocoloValidator.FORMATED_RENOVADO, "$1$2$3$4", ProtocoloValidator.UNFORMATED_RENOVADO);

		protocoloValido = formatterNovo.isFormatted(protocolo)
			? ProtocoloUtils.unformat(protocolo)
			: protocolo;
		return application.consulta(new Protocolo(protocoloValido, formatterNovo));
	}

	@Override
	public LicencaDTO consultarLicencaDTO(final String protocolo) {

		var licenca = consultar(protocolo);

		return new LicencaDTOAssembler().toDTO(licenca);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminho(final String protocolo, final Integer tipo) {
		var formatter = new ProtocoloFormatter();


		var formatterNovo = new ProtocoloFormatter("$1-$2/$3-$4", ProtocoloValidator.FORMATED_RENOVADO, "$1$2$3$4", ProtocoloValidator.UNFORMATED_RENOVADO);

		Protocolo protocoloValido;

		if(formatter.canBeFormatted(protocolo)){

			 protocoloValido = new Protocolo(
				formatter.isFormatted(protocolo)
					? ProtocoloUtils.unformat(protocolo)
					: protocolo
			);
		} else {
			protocoloValido = new Protocolo(
				formatterNovo.isFormatted(protocolo)
					? ProtocoloUtils.unformat(protocolo)
					: protocolo
			);
		}

		if (tipo.equals(Constants.BOLETO)) {

			return application.buscarCaminhoBoleto(protocoloValido);

		} else if (tipo.equals(Constants.CARTEIRA)) {

			return application.buscarCaminhoCarteira(protocoloValido);

		} else {

			return null;

		}

	}

	@Override
	public File gerarCarteira(Protocolo protocolo, Licenca licenca, Pessoa pessoa) throws Exception {

		Map<String,String> data = new HashMap<>();

		var qrcode = QRCodeUtils.createQRCodeImage(Properties.baseUrl() + "informacao-carteira" + "/" + protocolo.getProtocoloNaoFormatado());

		String qrcode64 = ImagemUtils.converBase64(qrcode, "png");
		String nomeFontSize = (pessoa.nome.toUpperCase().length() > 47) ? ("7px") : ("10px");
		String enderecoFontSize = (CarteiraBuilderImpl.campoEndereco(CarteiraBuilderImpl.endereco(pessoa)).length() > 47)?("7px") : ("10px");
		String municipioFontSize = (CarteiraBuilderImpl.campoMunicipioUF(CarteiraBuilderImpl.endereco(pessoa)).length() > 22)?("7px") : ("10px");

		data.put("qrcode", qrcode64);
		data.put("baseUrl", Properties.baseUrl());

		data.put("nome", pessoa.nome.toUpperCase());
		data.put("nomeFontSize",nomeFontSize);
		data.put("cpfPassaporte", CarteiraBuilderImpl.identificadorPessoa(pessoa));

		data.put("numLicenca", protocolo.getCodigoFormatado());
		data.put("modalidade", licenca.modalidade().getNomePT().toUpperCase());

		data.put("endereco", CarteiraBuilderImpl.campoEndereco(CarteiraBuilderImpl.endereco(pessoa)));
		data.put("enderecoFontSize",enderecoFontSize);

		data.put("municipioFontSize",municipioFontSize);
		data.put("municipioUF", CarteiraBuilderImpl.campoMunicipioUF(CarteiraBuilderImpl.endereco(pessoa)));
		data.put("cep", CarteiraBuilderImpl.campoCEP(CarteiraBuilderImpl.endereco(pessoa)));

		data.put("pais", "BRASIL");
		data.put("limiteCaptura", licenca.modalidade().getDescricaoQtdPeixesLimiteCaptura());

		data.put("mensagensDeAviso", licenca.mensagensDeAviso());
		data.put("descricaoCarteiraDefinitivaEProvisoria", licenca.descricaoCarteiraDefinitivaEProvisoria());

		if(licenca.dataCriacao() == null && licenca.dataAtivacao() == null) {
			data.put("emissao", "-");
		}

		if(licenca.getStatus().getId().equals(Status.StatusEnum.ATIVO_AGUARDANDO_PAGAMENTO.id)){
			data.put("emissao", DateUtils.formatDate(licenca.dataCriacao(), Constants.DATE_FORMAT));
		} else {
			data.put("emissao", DateUtils.formatDate(licenca.dataAtivacao(), Constants.DATE_FORMAT));
		}

		TaxaLicenca taxaLicenca = taxaLicencaRepository.findByLicencaAndPago(licenca, true);

		if(taxaLicenca != null) {

			LocalDate validade = licenca.getDataVencimento();
			data.put("validade", validade.format(Constants.FORMATO_DATA_PADRAO));

		} else {
			throw new ValidationException("Não é possivel emitir a licença, pois ainda não houve confirmação de pagamento do boleto.");
		}

		return pdfGenaratorUtil.createPdf("carteira",data);
	}

}
