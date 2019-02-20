package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import java.text.Normalizer;

public class StringUtils {

	public static String completaStringComZerosEsquerda(Integer tamanhoCampo, String valor) {
		return completaStringComEspacosEsquerda(tamanhoCampo, valor).replaceAll(" ", "0");
	}

	public static String completaStringComEspacosEsquerda(Integer tamanhoCampo, String valor) {
		return String.format("%1$" + tamanhoCampo + "s", valor);
	}

	public static String completaStringComEspacosDireita(Integer tamanhoCampo, String valor) {

		valor = removeCaracteresEspeciais(valor.toUpperCase());

		Integer tamanhoString = valor.length();

		if(tamanhoCampo == tamanhoString) {
			return valor;
		} else if(tamanhoCampo > tamanhoString) {
			return valor + String.format("%1$" + (tamanhoCampo - tamanhoString) + "s", "");
		} else {
			return valor.substring(0, tamanhoCampo - 1);
		}

	}

	public static String removeCaracteresEspeciais(String valor) {

		return Normalizer.normalize(valor, Normalizer.Form.NFD)
			.replaceAll("[^\\p{ASCII}]", "")
			.replaceAll("[&\\/\\\\#,ºª+()$~%.'\":*?<>{}]", "");

	}

}
