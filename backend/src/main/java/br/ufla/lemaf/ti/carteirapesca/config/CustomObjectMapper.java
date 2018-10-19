package br.ufla.lemaf.ti.carteirapesca.config;

import br.ufla.lemaf.ti.carteirapesca.interfaces.serializers.DateSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Classe responsavel pela configuração de
 * mapeamento de objeto. Adicionando a configuração
 * de ignorar falhas em propriedades desconhecidas
 * e incluir serializadores e deserializadores
 * customizados.
 *
 * @author Highlander Paiva
 * @since 0.1
 */

@Configuration
public class CustomObjectMapper extends ObjectMapper {

	private SimpleModule simpleModule;

	/**
	 * Inicializa o módulo e adiciona serializadores
	 * e deserializadores
	 */
	public CustomObjectMapper() {

		this.simpleModule = new SimpleModule();

		setDeserializers();
		setSerializers();

		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.registerModule(this.simpleModule);
		this.registerModule(new Jdk8Module());

	}

	/**
	 * Adiciona Deserializadores customizados
	 */
	private void setDeserializers() {

		this.simpleModule.addDeserializer(Date.class, new DateDeserializer());
		this.simpleModule.addDeserializer(String.class, new StringDeserializer());

	}

	/**
	 * Adiciona Serializadores customizados
	 */
	private void setSerializers() {

		this.simpleModule.addSerializer(Date.class, new DateSerializer());

	}

	/**
	 * Busca a URL do Entrada Unica
	 *
	 * @return URL da entrada Unica
	 */
	@Bean(name = "urlEntradaUnicaService")
	public UrlEntradaUnica getUrlEntradaUnica() {
		return () -> Config.ENTRADA_UNICA_URL_PORTAL_SEGURANCA;
	}

	/**
	 * Interface da URL da Entrada Unica
	 */
	public interface UrlEntradaUnica {
		String getUrlEntradaUnica();
	}


}
