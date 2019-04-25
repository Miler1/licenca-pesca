package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

	public static File moveArquivoParaDiretorio(File file, String diretorioSalvarComNomeArquivo) throws Exception {

		Path pathArquivoRetorno = Paths.get(diretorioSalvarComNomeArquivo);

		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno.getParent());
		}

		FileUtils.moveFileToDirectory(file, arquivoRetorno, true);

		return arquivoRetorno;

	}

}
