package br.ufla.lemaf.ti.carteirapesca.infrastructure.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Utilitario de mensagens.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Component
public class Message {

	private MessageSource messageSource;

	private static MessageSourceAccessor accessor;

	/**
	 * Injetando dependências.
	 *
	 * @param messageSource O interface para
	 *                      resolver mensagens
	 */
	@Autowired
	public Message(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Inicializador do componente de mensagem.
	 */
	@PostConstruct
	private void init() {
		setAccessor(new MessageSourceAccessor(messageSource));
	}

	/**
	 * Dado a chave da mensagem no arquivo de mensagens,
	 * retorna a mesma.
	 *
	 * @param messageKey O código da mensagem
	 * @param args       Os argumentos para a mensagem
	 * @return A mensagem
	 */
	public static String get(String messageKey, Object... args) {
		return accessor.getMessage(messageKey, args);
	}

	/**
	 * Setter do acessor de mensagem.
	 * <p>
	 * Usado para encapsular a atribuição estática do acessor
	 * em um método estático. Já que será usado em {@link #init()}
	 * que não é estático.
	 *
	 * @param messageSourceAccessor O Acessor de mensagem
	 */
	private static void setAccessor(MessageSourceAccessor messageSourceAccessor) {
		accessor = messageSourceAccessor;
	}
}
