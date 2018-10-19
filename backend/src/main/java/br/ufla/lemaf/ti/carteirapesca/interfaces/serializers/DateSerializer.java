package br.ufla.lemaf.ti.carteirapesca.interfaces.serializers;

import br.ufla.lemaf.ti.carteirapesca.application.utils.ParserUtil;
import br.ufla.lemaf.ti.carteirapesca.config.Config;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import flexjson.transformer.DateTransformer;
import flexjson.transformer.Transformer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Converte datas em string para Date.
 *
 * @since 0.1
 */
public class DateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

		if(DateUtils.isSameDay(date, new Date(ParserUtil.FIRST_MILLISECONDS_DATE))) {

			jsonGenerator.writeString(ParserUtil.formatTimetable(date));

			return;

		}

		jsonGenerator.writeString(ParserUtil.format(date));

	}

	public static Transformer getTransformer() {

		return new DateTransformer(Config.DATE_FORMAT);

	}

	public static Transformer getTransformerWithTimetable() {

		return new DateTransformer(Config.DATE_FORMAT_TIMETABLE);

	}


}
