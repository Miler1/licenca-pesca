package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerador {

	private Scanner sc;

	private String[] gerar(String file, Integer tamanho, Integer padrao) throws IOException {

		try {

			ClassPathResource resource = new ClassPathResource(file);
			InputStream resourceInputStream = resource.getInputStream();

			sc = new Scanner(resourceInputStream);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		List<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {

			lines.add(sc.nextLine());

		}

		sc.close();

		padrao = padrao == 0 ? 5 : padrao;

		Integer index = padrao;

		String[] nomes = new String[tamanho];

		for(int i = 0; i < tamanho; i++){

			nomes[i] = lines.get(index);

			index += padrao;

		}
		return nomes;
	}

	public String[] gerarMaes(Integer tamanho, Integer padrao) throws IOException {
		return gerar("gerador/maes", tamanho, padrao);

	}

}
