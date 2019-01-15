package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerador {

	private Scanner sc;

	private String[] gerar(String file, Integer tamanho, Integer padrao){

		try {

			sc = new Scanner(new File(file));

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
	public String[] gerarMaes(Integer tamanho, Integer padrao){
		return gerar(Properties.baseUrl()  + "/maes/maes", tamanho, padrao);
	}


}
