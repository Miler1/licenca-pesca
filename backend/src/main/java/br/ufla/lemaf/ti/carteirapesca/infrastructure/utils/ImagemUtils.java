package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImagemUtils {

	public static String converBase64(BufferedImage image, String type) {

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
