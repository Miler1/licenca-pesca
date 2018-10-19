package br.ufla.lemaf.ti.carteirapesca.interfaces.deserializers;

import br.ufla.lemaf.ti.carteirapesca.application.utils.ParserUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Deserializador de datas
 *
 * @since 0.1
 */
public class DateDeserializer extends JsonDeserializer<Date> {

	/**
	 * Método de deserialização
	 * A cada formato de data, retornará o valor da data com um conversor diferente
	 * de ParseUtil.
	 * @param jsonParser
	 * @param deserializationContext
	 * @return data convertida
	 * @throws IOException
	 */
	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

		JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

		String date = jsonNode.asText();

		if(StringUtils.isNumeric(date) || date.substring(0,1).equals("-")) {

			return new Date(Long.valueOf(date));

		} else if(date.matches("(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z")) {

			return ParserUtil.parseDateISO(date);

		} else if(date.matches("(\\d{2}):(\\d{2})")) {

			return ParserUtil.parseTimeOnly(date);

		} else if(date.length() <= 10) {

			return ParserUtil.parseDate(date);

		} else {

			return ParserUtil.parseDateTimetable(date);

		}

	}

}
