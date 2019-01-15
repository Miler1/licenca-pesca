package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.CarteiraBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.*;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTOAssembler;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
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

	@Override
	public File gerarCarteira(Protocolo protocolo, Licenca licenca, Pessoa pessoa) throws Exception {

		Map<String,String> data = new HashMap<String,String>();

		var qrcode = QRCodeUtils.createQRCodeImage(protocolo.getProtocoloNaoFormatado());

		String qrcode64 = encodeToString(qrcode, "png");
		String nomeFontSize = (pessoa.nome.toUpperCase().length() > 47) ? ("7px") : ("10px");
		String enderecoFontSize = (CarteiraBuilderImpl.campoEndereco(CarteiraBuilderImpl.endereco(pessoa)).length() > 47)?("7px") : ("10px");

		System.out.println("Length carteira:" + CarteiraBuilderImpl.campoEndereco(CarteiraBuilderImpl.endereco(pessoa)).length());

		data.put("qrcode", qrcode64);
		data.put("baseUrl", Properties.baseUrl());

		data.put("nome", pessoa.nome.toUpperCase());
		data.put("nomeFontSize",nomeFontSize);
		data.put("cpfPassaporte", CarteiraBuilderImpl.identificadorPessoa(pessoa));

		data.put("numLicenca", protocolo.getCodigoFormatado());
		data.put("modalidade", licenca.modalidade().name().toUpperCase());

		data.put("endereco", CarteiraBuilderImpl.campoEndereco(CarteiraBuilderImpl.endereco(pessoa)));
		data.put("enderecoFontSize",enderecoFontSize);

		data.put("municipioUF", CarteiraBuilderImpl.campoMunicipioUF(CarteiraBuilderImpl.endereco(pessoa)));
		data.put("cep", CarteiraBuilderImpl.campoCEP(CarteiraBuilderImpl.endereco(pessoa)));

		data.put("pais", "BRASIL");
		data.put("limiteCaptura", CarteiraBuilderImpl.campoLimiteCaptura(licenca.modalidade()));

		Date validade = new Date();
		validade.setYear(validade.getYear()+1);

		data.put("emissao", DateUtils.formatDate(new Date(), Constants.DATE_FORMAT));
		data.put("validade", DateUtils.formatDate(validade, Constants.DATE_FORMAT));

		return pdfGenaratorUtil.createPdf("carteira",data);
	}


	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			Base64.Encoder encoder = Base64.getEncoder();
			imageString = encoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}
}
