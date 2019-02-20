package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Beneficiario;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Convenio;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.PagadorTitulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ConvenioBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.PdfGeneratorUtil;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BaseFont;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConvenioBuilderImpl implements ConvenioBuilder {

	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	@Autowired
	PdfGeneratorUtil pdfGenaratorUtil;

	@Override
	public Titulo gerarDocumentoArrecadacao(Protocolo protocolo, Modalidade modalidade, Pessoa pessoa) throws IOException, DocumentException {

		gerarCodigoBarras("83620000000-5 72950138000-4 26497378133-1 08070582559-6");

		return null;
	}

	private Convenio gerarConvenio(Modalidade modalidade, Pessoa pessoa) {

	}

	private File gerarDocumentoPagamento(Convenio convenio) throws Exception {

		Map<String, String> dadosDocumento = new HashMap<>();

		Beneficiario beneficiario = convenio.getBeneficiario();
		dadosDocumento.put("nomeBeneficiario", beneficiario.getNome());
		dadosDocumento.put("cnpjBeneficario", "CNPJ: " + beneficiario.getCpfCnpj());
		dadosDocumento.put("enderecoBeneficiario", beneficiario.getEndereco().getDescricaoEndereco());
		dadosDocumento.put("cepBeneficiario", beneficiario.getEndereco().getCep());
		dadosDocumento.put("municipioBeneficiario", beneficiario.getEndereco().getMunicipio());
		dadosDocumento.put("ufBeneficiario", beneficiario.getEndereco().getEstado());

		dadosDocumento.put("dataEmissao", convenio.getDataEmissao().format(FORMATO_DATA));
		dadosDocumento.put("dataVencimento", convenio.getDataVencimento().format(FORMATO_DATA));
		dadosDocumento.put("valor", "R$ " + convenio.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN));

		PagadorTitulo pagador = convenio.getPagador();
		dadosDocumento.put("nomePagador", pagador.getNome());
		dadosDocumento.put("cpfPassaportePagador", pagador.getCpfPassaporte());
		dadosDocumento.put("enderecoPagador", pagador.getEndereco().getDescricaoEndereco());
		dadosDocumento.put("municipioPagador", pagador.getEndereco().getMunicipio());
		dadosDocumento.put("ufPagador", pagador.getEndereco().getEstado());
		dadosDocumento.put("cepPagador", pagador.getEndereco().getCep());

		dadosDocumento.put("codigoLicenca", "<INSERIR>");
		dadosDocumento.put("modalidaLicenca", "<INSERIR>");
		dadosDocumento.put("limiteCapturaLicenca", "<INSERIR>");

		dadosDocumento.put("linhaDigitavel", convenio.getCodigoBarras());
		dadosDocumento.put("pathImagemCodigoBarras", gerarCodigoBarras("83620000000-5 72950138000-4 26497378133-1 08070582559-6"));

		return pdfGenaratorUtil.createPdf("cobranca", dadosDocumento);

	}

	private String gerarCodigoBarras(String linhaDigitavel) throws IOException, DocumentException {

		BarcodeInter25 codigoBarras = new BarcodeInter25();
		codigoBarras.setGenerateChecksum(false);
		codigoBarras.setCode(linhaDigitavel);
		codigoBarras.setSize(9);
		codigoBarras.setBarHeight(35);
		codigoBarras.setBaseline(12);
		codigoBarras.setTextAlignment(3);
		codigoBarras.setStartStopText(true);
		codigoBarras.setChecksumText(true);

		BaseFont baseFontStartStop = BaseFont.createFont();
		codigoBarras.setFont(baseFontStartStop);

		return geraImagemCodigoBarras(codigoBarras);

	}

	private String geraImagemCodigoBarras(BarcodeInter25 codigoBarras) throws IOException {

		java.awt.Image imagemAwt = codigoBarras.createAwtImage(Color.BLACK, Color.WHITE);

		BufferedImage bimage = new BufferedImage(imagemAwt.getWidth(null), imagemAwt.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(imagemAwt, 0, 0, null);
		bGr.dispose();

		File file  = new File(UUID.randomUUID() + ".png");
		ImageIO.write(bimage, "png", file);

		return file.getAbsolutePath();

	}

}
