package br.ufla.lemaf.ti.carteirapesca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Classe principal da Aplicação.
 *
 * @author Highlander Paiva
 */
@Configuration
@SpringBootApplication
public class CarteiraPescaApplication {

	/**
	 * Classe main.
	 *
	 * @param args Os argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CarteiraPescaApplication.class, args);
	}
}
