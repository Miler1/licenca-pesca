package br.ufla.lemaf.ti.carteirapesca.interfaces.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Gera mensagens para o usuario a partir de
 * <b>messages.properties</b>
 *
 * @since 0.1
 */
@Component
public class Mensagem {

	private MessageSource messageSource;

	@Autowired
	public Mensagem(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	Locale ptBr = new Locale("pt", "BR");

	private static MessageSourceAccessor accessor;

	/**
	 * Instancia a mensagem
	 */
	@PostConstruct
	private void init() {

		accessor = new MessageSourceAccessor(messageSource, ptBr);

	}

	/**
	 * Getter de mensagem
	 *
	 * @param code codigo
	 * @param args argumentos
	 * @return {String} mensagem
	 */
	public static String get(String code, Object... args) {

		return accessor.getMessage(code, args);

	}


}

