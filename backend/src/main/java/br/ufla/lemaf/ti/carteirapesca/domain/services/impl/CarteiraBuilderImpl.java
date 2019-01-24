package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.CarteiraBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.QRCodeUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.EntradaUnicaException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade.*;

/**
 * Buider da Carteira.
 * <p>
 * Cria o arquivo da carteira de pesca.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
@Service
public class CarteiraBuilderImpl implements CarteiraBuilder {

	private static final String TEMPLATE_CARTEIRA = "templates/template_carteira_pesca.png";
	private static final Integer FONT_SIZE = 26;

	private static final Integer EIXO_X_COLUNA_1 = 80;
	private static final Integer EIXO_X_COLUNA_2 = 710;
	private static final Integer EIXO_X_COLUNA_3 = EIXO_X_COLUNA_2 + 720;
	private static final Integer EIXO_X_COLUNA_4 = EIXO_X_COLUNA_3 + 630;

	private static final Integer EIXO_Y_LINHA_1 = 280;
	private static final Integer EIXO_Y_LINHA_2 = EIXO_Y_LINHA_1 + 100;
	private static final Integer EIXO_Y_LINHA_3 = EIXO_Y_LINHA_2 + 100;
	private static final Integer EIXO_Y_LINHA_4 = EIXO_Y_LINHA_3 + 100;
	private static final Integer EIXO_Y_LINHA_5 = EIXO_Y_LINHA_4 + 100;
	private static final Integer EIXO_Y_LINHA_1_COLUNA_2 = EIXO_Y_LINHA_3 + 60;

	private static final Integer EIXO_X_QRCODE = 2200;
	private static final Integer EIXO_Y_QRCODE = 100;

	private static final String FORMAT_CARTEIRA = "png";

	@Override
	public String gerarCarteira(final Protocolo protocolo,
	                            final Modalidade modalidade,
	                            final Pessoa pessoa) {

		try {

			val carteiraLayout = gerarCarteiraLayout(protocolo, modalidade, pessoa);

			var caminhoCarteira = Paths.get(
				Properties.pathCarteiraPesca()
					+ "/" + protocolo.getProtocoloNaoFormatado()
					+ "." + FORMAT_CARTEIRA
			);

			var carteiraPesca = caminhoCarteira.toFile();

			if (!carteiraPesca.exists())
				Files.createDirectories(caminhoCarteira.getParent());

			ImageIO.write(carteiraLayout, FORMAT_CARTEIRA, carteiraPesca);

			return caminhoCarteira.toString();

		} catch (IOException | NullPointerException e) {

			log.error(e.getMessage());

			throw new ResourceNotFoundException();

		}
	}

	/**
	 * Gera o layout da carteira de pesca.
	 *
	 * @param protocolo O Protocolo
	 * @param modalidade A modalidade
	 * @param pessoa A pessoa solicitante
	 * @return A imagem da carteira
	 */
	private BufferedImage gerarCarteiraLayout(final Protocolo protocolo,
	                                        final Modalidade modalidade,
	                                        final Pessoa pessoa) {
		try {

			val classLoader = getClass().getClassLoader();
			val carteira = ImageIO.read(classLoader.getResource(TEMPLATE_CARTEIRA).openStream());
			var grafics = carteira.getGraphics();

			grafics.setColor(Color.BLACK);
			grafics.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));

			// Nome
			grafics.drawString(pessoa.nome.toUpperCase(), EIXO_X_COLUNA_1, EIXO_Y_LINHA_1);
			grafics.drawString(identificadorPessoa(pessoa), EIXO_X_COLUNA_2, EIXO_Y_LINHA_1);

			grafics.drawString(protocolo.getCodigoFormatado(), EIXO_X_COLUNA_1, EIXO_Y_LINHA_2);
			grafics.drawString(modalidade.getNomePT().toUpperCase(), EIXO_X_COLUNA_2, EIXO_Y_LINHA_2);

			grafics.drawString(campoEndereco(endereco(pessoa)), EIXO_X_COLUNA_1, EIXO_Y_LINHA_3);

			grafics.drawString(campoMunicipioUF(endereco(pessoa)), EIXO_X_COLUNA_1, EIXO_Y_LINHA_4);
			grafics.drawString(campoCEP(endereco(pessoa)), EIXO_X_COLUNA_2, EIXO_Y_LINHA_4);

			grafics.drawString("BRASIL", EIXO_X_COLUNA_1, EIXO_Y_LINHA_5);
			grafics.drawString(campoLimiteCaptura(modalidade), EIXO_X_COLUNA_2, EIXO_Y_LINHA_5);

//			grafics.drawString(campoValorCarteira(modalidade), EIXO_X_COLUNA_1, EIXO_Y_LINHA_6);

			Date validade = new Date();
			validade.setYear(validade.getYear()+1);

			// Validade
			grafics.drawString(
				DateUtils.formatDate(validade, Constants.DATE_FORMAT),
				EIXO_X_COLUNA_4,
				EIXO_Y_LINHA_1_COLUNA_2
			);

			// Emissao
			grafics.drawString(
				DateUtils.formatDate(new Date(), Constants.DATE_FORMAT),
				EIXO_X_COLUNA_3,
				EIXO_Y_LINHA_1_COLUNA_2
			);

			grafics.drawImage(generateQRCode(protocolo), EIXO_X_QRCODE, EIXO_Y_QRCODE, null);

			grafics.dispose();

			return carteira;

		} catch (IOException | NullPointerException e) {

			log.error(e.getMessage());

			throw new ResourceNotFoundException();
		}
	}

	/**
	 * Busca o dado para o campo de CPF/PASSAPORTE.
	 *
	 * @param pessoa A Pessoa solicitante da carteira
	 * @return O CPF ou o Passsaporte da pessoa solicitante
	 */
	public static String identificadorPessoa(Pessoa pessoa) {
		if (pessoa.cpf != null) {

			return CPFUtils.format(pessoa.cpf);

		} else if (pessoa.passaporte != null) {

			return pessoa.passaporte.toString().toUpperCase();

		} else {

			throw new EntradaUnicaException("");

		}
	}

	/**
	 * Busca o endereço da carteira.
	 *
	 * @param pessoa A Pessoa solicitante
	 * @return O endereço
	 */
	public static Endereco endereco(Pessoa pessoa) {
		return pessoa.enderecos
			.stream()
			.filter(endereco ->
				StringUtils.isNotBlank(endereco.logradouro) && StringUtils.isNotBlank(endereco.cep))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * Constroi o campo de endereço.
	 *
	 * @param endereco O endereço da carteira
	 * @return O campo de endereço
	 */
	public static String campoEndereco(Endereco endereco) {
		return (endereco.logradouro + ", Nº " + endereco.numero + " "
			+ (endereco.complemento != null ? endereco.complemento : "") + ", " + endereco.bairro)
			.toUpperCase();
	}

	/**
	 * Constroi o campo de município e UF.
	 *
	 * @param endereco O endereço da carteira
	 * @return O campo de município e UF
	 */
	public static String campoMunicipioUF(Endereco endereco) {
		return (endereco.municipio.nome + " / " + endereco.municipio.estado.sigla)
			.toUpperCase();
	}

	/**
	 * Constroi o campo de CEP.
	 *
	 * @param endereco O endereço da carteira
	 * @return O campo de CEP
	 */
	public static String campoCEP(Endereco endereco) {
		return endereco.cep != null ? endereco.cep : "-";
	}

	/**
	 * Constroi o campo limite de captura.
	 *
	 * @param modalidade A modalidade da carteira
	 * @return O campo de limite de captura
	 */
	public static String campoLimiteCaptura(Modalidade modalidade) {
		if(modalidade.getId().equals(Modalidades.PESCA_ESPORTIVA.id)) {

			return "0 (ZERO)";
		} else if(modalidade.getId().equals(Modalidades.PESCA_REACREATIVA.id)) {

			return "05 KG DE PEIXES INTEIROS";
		} else {
			return "";
		}

	}

	/**
	 * Constroi o campo valor carteira.
	 * <p>
	 *
	 * @deprecated O campo valor será removido da carteira de pesca.
	 * @param modalidade A modalidade da carteira
	 * @return O campo de valor da carteira
	 */
	@Deprecated
	private static String campoValorCarteira(Modalidade modalidade) {
		if(modalidade.getId().equals(Modalidades.PESCA_ESPORTIVA.id)) {

			return "R$41,20";
		} else if(modalidade.getId().equals(Modalidades.PESCA_REACREATIVA.id)) {

			return "R$57,21";
		} else {
			return "";
		}

	}

	/**
	 * Gera o QR Code para a carteira de pesca.
	 *
	 * @param protocolo O protocolo
	 * @return A imagem gerada do QR Code
	 */
	private static BufferedImage generateQRCode(Protocolo protocolo) {
		return QRCodeUtils.createQRCodeImage(Properties.baseUrl() + protocolo.getCodigo());
	}
}
