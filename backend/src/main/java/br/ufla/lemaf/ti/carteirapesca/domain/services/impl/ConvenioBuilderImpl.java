package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoSegmentoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoValorEfetivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.BeneficiarioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.ConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoSegmentoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoValorEfetivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ConvenioBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.BancoUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ImagemUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.PdfGeneratorUtil;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Slf4j
@Service
public class ConvenioBuilderImpl implements ConvenioBuilder {

	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FORMATO_DATA_CODIGO_BARRAS = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final String ESTENSAO_IMAGEM = "png";

	@Autowired
	PdfGeneratorUtil pdfGenaratorUtil;

	@Autowired
	TipoSegmentoRepository tipoSegmentoRepository;

	@Autowired
	TipoValorEfetivoRepository tipoValorEfetivoRepository;

	@Autowired
	BeneficiarioRepository beneficiarioRepository;

	@Autowired
	PagadorBuilderImpl pagadorBuilder;

	@Autowired
	ConvenioRepository convenioRepository;

	@Override
	public Convenio geraConvenio(Modalidade modalidade, Pessoa pessoa) {

		//TODO Hiago - Alterar tipo de segmento de "TipoSegmentoEnum.ENERGIA_GAS" para "TipoSegmentoEnum.ORGAO_GOVERNAMENTAL"
		TipoSegmento tipoSegmento = tipoSegmentoRepository.findByCodigo(TipoSegmentoEnum.DEMAIS_EMPRESAS.getCodigo());
		TipoValorEfetivo tipoValorEfetivo = tipoValorEfetivoRepository.findByCodigo(TipoValorEfetivoEnum.VALOR_REAIS_MODULO_10.getCodigo());
		PagadorTitulo pagadorTitulo = pagadorBuilder.transformarPessoaEmPagador(pessoa);
		Beneficiario beneficiario = beneficiarioRepository.findBySigla("IPAAM");

		Convenio convenio = new Convenio(tipoSegmento, tipoValorEfetivo, pagadorTitulo, beneficiario, modalidade.getValor());

		convenio.setNossoNumero(convenioRepository.count());
		convenio.setCodigoBarras(geraLinhaDigitavel(convenio));

		return convenio;

	}

	@Override
	public File geraDocumentoArrecadacao(Licenca licenca) throws Exception {

		Map<String, String> dadosDocumento = new HashMap<>();

		Convenio convenio = licenca.getConvenio();

		Beneficiario beneficiario = convenio.getBeneficiario();
		dadosDocumento.put("baseUrl", Properties.baseUrl());
		dadosDocumento.put("nomeBeneficiario", beneficiario.getNome());
		dadosDocumento.put("cnpjBeneficario", "CNPJ: " + beneficiario.getCpfCnpj());
		dadosDocumento.put("enderecoBeneficiario", beneficiario.getEndereco().getDescricaoEndereco());

		String cepMunicipioUfBeneficiario = beneficiario.getEndereco().getCep() + " - " +
			beneficiario.getEndereco().getMunicipio() + " - " +
			beneficiario.getEndereco().getEstado();

		dadosDocumento.put("cepMunicipioUfBeneficiario", cepMunicipioUfBeneficiario);
		dadosDocumento.put("dataEmissao", convenio.getDataEmissao().format(FORMATO_DATA));
		dadosDocumento.put("dataVencimento", convenio.getDataVencimento().format(FORMATO_DATA));
		dadosDocumento.put("valor", "R$ " + convenio.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));

		PagadorTitulo pagador = convenio.getPagador();
		dadosDocumento.put("nomePagador", pagador.getNome());
		dadosDocumento.put("cpfPassaportePagador", (pagador.getCpfPassaporte().length() == 11 ? CPFUtils.format(pagador.getCpfPassaporte()) : pagador.getCpfPassaporte()));
		dadosDocumento.put("enderecoPagador", pagador.getEndereco().getDescricaoEndereco());
		dadosDocumento.put("municipioUfPagador", pagador.getEndereco().getMunicipio() + "/" + pagador.getEndereco().getEstado()) ;
		dadosDocumento.put("cepPagador", pagador.getEndereco().getCep());
		dadosDocumento.put("codigoLicenca", licenca.getProtocolo().toString());
		dadosDocumento.put("modalidaLicenca", licenca.getModalidade().getNomePT());
		dadosDocumento.put("limiteCapturaLicenca", licenca.getModalidade().getDescricaoQtdPeixesLimiteCaptura());

		dadosDocumento.put("linhaDigitavel", convenio.getCodigoBarras());
		dadosDocumento.put("imagemCodigoBarras", ImagemUtils.converBase64(geraImagemCodigoBarras(convenio.getCodigoBarras()), ESTENSAO_IMAGEM) );

		return pdfGenaratorUtil.createPdf("cobranca", dadosDocumento);

	}

	/** O dígito auto-conferência é calculado aplicando base 2 e módulo de 10
	 *  Para o calculo deve ser informado o bloco ao qual o digito será calculado */
	private Integer calculaDigititoAutoConferenciaModuloDez(String blocoCodigoBarras) {

		Integer base = 2;
		Integer numeroPosicao, resultadoMultiplicacao, resultadoSoma = 0;
		Integer indexInicio, indexFinal;

		for(int i = 0; i < blocoCodigoBarras.length(); i++) {

			if(base < 1) {
				base = 2;
			}

			indexInicio = (blocoCodigoBarras.length() - 1 - i);
			indexFinal = blocoCodigoBarras.length() - i;

			numeroPosicao = Integer.valueOf(blocoCodigoBarras.substring(indexInicio, indexFinal));

			resultadoMultiplicacao = numeroPosicao * base;

			/**Quando o resultado maior que 9 os digitos deverão ser somados de forma individual
			 * ex: resultado = 18 -> a soma deverá ser 1 + 8 -> resultando em 9*/
			if(resultadoMultiplicacao > 9) {

				Integer primeiroDigito = Integer.valueOf(resultadoMultiplicacao.toString().substring(0, 1));
				Integer segundoDigito = Integer.valueOf(resultadoMultiplicacao.toString().substring(1, 2));

				resultadoSoma = resultadoSoma + primeiroDigito + segundoDigito;

			} else {
				resultadoSoma = resultadoSoma + resultadoMultiplicacao;
			}

			base--;

		}

		Integer modulo = 10;
		Integer restoDivisao = resultadoSoma % modulo;

		/**Se o resto da divisão for igual a 0 (zero) o dígito de auto-conferência será 0 (zero)*/
		return (restoDivisao > 0 ? modulo - restoDivisao : restoDivisao);

	}

	private String geraLinhaDigitavel(Convenio convenio) {

		StringBuffer linhaDigitavel = new StringBuffer();

		/** Id do produto é uma contante para identificar a arrecadaçãa (convênio)*/
		String idProduto = "8";
		String valor = BancoUtils.removeFormatacaoValorMonetario(convenio.getValor());
		String dataVencimento = convenio.getDataVencimento().format(FORMATO_DATA_CODIGO_BARRAS);

		/** Código da empresa deverá ser os 8 primeiros digítos do CNPJ (antes da '/')*/
		String codigoEmpresa = convenio.getBeneficiario().getCpfCnpj().substring(0, 8);

		/** Tamanho máximo campo livre será de 21 quando o CNPJ for utilizado como código da empresa (antes da '/')*/
		Integer tamanhoMaximoCampoLivre = 21;

		linhaDigitavel.append(idProduto)
			.append(convenio.getTipoSegmento().getCodigo())
			.append(convenio.getTipoValorEfetivo().getCodigo())
			.append(BancoUtils.completaStringComZerosEsquerda(11, valor))
			.append(codigoEmpresa)
			.append(dataVencimento)
			.append(BancoUtils.completaStringComZerosEsquerda( tamanhoMaximoCampoLivre - dataVencimento.length(),
				convenio.getNossoNumero().toString()));

		Integer digitoAutoConferenciaGeral = calculaDigititoAutoConferenciaModuloDez(linhaDigitavel.toString());

		/** Inseri digito de auto-conferencia geral*/
		String linhaDigitavelCompleta = linhaDigitavel.toString().substring(0, 3) +
			digitoAutoConferenciaGeral +
			linhaDigitavel.toString().substring(3, 43);

		String bloco1 = formatarLinhaDigitavelPorBloco(linhaDigitavelCompleta.substring(0, 11));
		String bloco2 = formatarLinhaDigitavelPorBloco(linhaDigitavelCompleta.substring(11, 22));
		String bloco3 = formatarLinhaDigitavelPorBloco(linhaDigitavelCompleta.substring(22, 33));
		String bloco4 = formatarLinhaDigitavelPorBloco(linhaDigitavelCompleta.substring(33, 44));

		return (bloco1 + bloco2 + bloco3 + bloco4).trim();

	}

	private String formatarLinhaDigitavelPorBloco(String blocoCodigoBarras) {

		StringBuffer linhaDigitavelFormatada = new StringBuffer();

		linhaDigitavelFormatada.append(blocoCodigoBarras)
			.append("-")
			.append(calculaDigititoAutoConferenciaModuloDez(blocoCodigoBarras))
			.append(" ");

		return linhaDigitavelFormatada.toString();
		
	}
	
	private BufferedImage geraImagemCodigoBarras(String linhaDigitavel) throws IOException, DocumentException {

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

	private BufferedImage geraImagemCodigoBarras(BarcodeInter25 codigoBarras) throws IOException {

		java.awt.Image imagemAwt = codigoBarras.createAwtImage(Color.BLACK, Color.WHITE);

		BufferedImage bimage = new BufferedImage(imagemAwt.getWidth(null), imagemAwt.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(imagemAwt, 0, 0, null);
		bGr.dispose();

		File file = File.createTempFile(UUID.randomUUID().toString(), ESTENSAO_IMAGEM);
		ImageIO.write(bimage, ESTENSAO_IMAGEM, file);

		return ImageIO.read(file);

	}

}
