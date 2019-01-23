package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

public class CarteiraUtils {

	public static String capitalize(String s) {

		if (s.length() == 0) {
			return s;
		}

		String capitalized = "";

		for (String aux : s.split(" ")) {

			if(aux.length() > 0) {
				capitalized += aux.substring(0, 1).toUpperCase() + aux.substring(1).toLowerCase() + " ";
			}

		}

		return capitalized.substring(0, capitalized.length()-1);

	}
}
