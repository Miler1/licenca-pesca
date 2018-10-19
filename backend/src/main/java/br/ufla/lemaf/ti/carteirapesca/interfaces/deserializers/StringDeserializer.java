package br.ufla.lemaf.ti.carteirapesca.interfaces.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Deserilizador de Strings
 * Retorna null se a string for vazia.
 *
 * @since 0.1
 */
public class StringDeserializer extends JsonDeserializer<String> {

	/**
	 * Método principal de deserialização
	 *
	 * @param jsonParser
	 * @param deserializationContext
	 * @return String
	 * @throws IOException
	 */
	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

		JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

		String string = jsonNode.asText();

		return string.isEmpty() || string.trim().isEmpty() ? null : string;

	}

}

