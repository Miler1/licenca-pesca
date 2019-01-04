package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

/**
 * Classe utilitária para gerar QRCodes.
 *
 * @author Marcio Azevedo
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
public final class QRCodeUtils {

	private static final Integer QRCODE_SIZE = 300;

	/**
	 * Retorna o codigo base64 da imagem do QRCode
	 * do código informado.
	 *
	 * @param code O código do QRCode
	 * @return A imagem do QRCode
	 */
	public static BufferedImage createQRCodeImage(String code) {

		try {

			var hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.MARGIN, 1);
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			var writer = new QRCodeWriter();
			var byteMatrix = writer.encode(
				code,
				BarcodeFormat.QR_CODE,
				QRCodeUtils.QRCODE_SIZE,
				QRCodeUtils.QRCODE_SIZE,
				hintMap
			);

			int size = byteMatrix.getWidth();
			var image = new BufferedImage(
				size,
				size,
				BufferedImage.TYPE_INT_RGB
			);
			image.createGraphics();

			var graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, size, size);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

			return image;

		} catch (WriterException e) {

			log.error(e.getMessage());

		}

		return null;
	}

	/**
	 * Pra evitar instanciação.
	 */
	private QRCodeUtils() {
	}
}
