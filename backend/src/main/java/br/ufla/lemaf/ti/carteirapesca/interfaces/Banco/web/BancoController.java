package br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.web;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RemessaBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RetornoBuilderImpl;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
@Controller
@Transactional
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private RemessaBuilderImpl remessaBuilder;

	@Autowired
	private RetornoBuilderImpl retornoBuilder;

	@CrossOrigin("*")
	@GetMapping("/gera-remessa")
	public ResponseEntity<InputStreamResource> geraRemessa() throws IOException {

		Remessa remessa = remessaBuilder.geraRemessa();

		if (remessa != null) {
			return preparaArquivoDownload(remessa.getArquivo());
		} else {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		}

	}

	@CrossOrigin("*")
	@GetMapping("/download-remessa/{idRemessa}")
	public ResponseEntity<InputStreamResource> downloadArquivoRemessa(@PathVariable("idRemessa") Integer idRemessa) throws FileNotFoundException {

		Arquivo arquivoRemessa = remessaBuilder.getArquivoRemessa(idRemessa);

		return preparaArquivoDownload(arquivoRemessa);

	}

	private ResponseEntity<InputStreamResource> preparaArquivoDownload(Arquivo arquivo) throws FileNotFoundException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType("application/octet-stream"));
		httpHeaders.setContentDispositionFormData("attachment", arquivo.getNome());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(arquivo.getCaminhoArquivo())));

		return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

	}

	@PostMapping("/upload-retorno")
	public ResponseEntity<String> uploadArquivoRetorno(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		Retorno retorno = retornoBuilder.salvaArquivo(multipartFile);

		retornoBuilder.processarRetorno(retorno);

		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

	}

	@CrossOrigin("*")
	@GetMapping("/lista-remessa")
	public ResponseEntity<Page<Remessa>> listaRemessasPaginado(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		Page<Remessa> remessas = remessaBuilder.listaRemessas(pageable);

		return new ResponseEntity<>(remessas, HttpStatus.OK);

	}

	@CrossOrigin("*")
	@GetMapping("/teste")
	public ResponseEntity<InputStreamResource> teste() throws IOException, DocumentException {

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("codebars_teste_123.pdf"));
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		document.add(new Paragraph("Barcode Interleaved 2 of 5 - Com START"));
		BarcodeInter25 code25StartStop = new BarcodeInter25();
		code25StartStop.setGenerateChecksum(false);
		code25StartStop.setCode("83620000000-5 72950138000-4 26497378133-1 08070582559-6");
		code25StartStop.setSize(9);
		code25StartStop.setBarHeight(35);
		code25StartStop.setBaseline(12);
		code25StartStop.setTextAlignment(3);
		code25StartStop.setStartStopText(true);
		code25StartStop.setChecksumText(true);

		BaseFont baseFontStartStop = BaseFont.createFont();
		code25StartStop.setFont(baseFontStartStop);

		java.awt.Image imagemAwt = code25StartStop.createAwtImage(Color.BLACK, Color.WHITE);

		BufferedImage bimage = new BufferedImage(imagemAwt.getWidth(null), imagemAwt.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(imagemAwt, 0, 0, null);
		bGr.dispose();

		File file  = new File("codigo_barras.png");
		ImageIO.write(bimage, "png", file);

		Image imageStartStop = code25StartStop.createImageWithBarcode(cb, Color.BLUE, Color.RED);


		document.add(imageStartStop);

		document.close();


		var httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_PDF);

		var isr = new InputStreamResource(new FileInputStream(file.getAbsoluteFile()));

		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

	}

	private void gerarCodigoBarras() {

		String idProduto;
		String idSegmento;
		String idValorReferencia;




	}

}
