package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoUtils {

	public static File salvaArquivoDiretorio(MultipartFile multipartFile, String diretorioSalvarComNomeArquivo) throws Exception {

		Path pathArquivoRetorno = Paths.get(diretorioSalvarComNomeArquivo);

		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno.getParent());
		}

		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), arquivoRetorno);

		return arquivoRetorno;

	}

	public static File salvaArquivoDiretorio(File file, String diretorioSalvarComNomeArquivo) throws Exception {

		Path pathArquivoRetorno = Paths.get(diretorioSalvarComNomeArquivo);

		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno.getParent());
		}

		FileUtils.copyInputStreamToFile(new FileInputStream(file), arquivoRetorno);

		return arquivoRetorno;

	}

}
