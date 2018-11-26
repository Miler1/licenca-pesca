package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Fábrica de Protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
class ProtocoloBuilder {

	private static final Integer SEQUENCE_PADDING = 4;

	private static final String DIVISOR_PRIMEIRA_PARTE = "-";

	private static final String DIVISOR_SEGUNDA_PARTE = "/";

	private static final String PREFIX_ESPORTIVA = "LPE";

	private static final String PREFIX_RECREATIVA = "LPR";

	@Autowired
	private SequenceRepository repository;

	/**
	 * Gerador de protocolo. Cria o protocolo
	 * seguindo o modelo em regra:
	 * <p>
	 * <b>LPX-9999/AA</b>
	 * <ul>
	 *   <li>
	 *     <i>LPX: </i>Prefixo que informa a modalidade.
	 *     <i>(Licença Pesca X, no qual X pode ser E, esportiva, ou R, recreativa)</i>
	 *   </li>
	 *   <li><i>9999: </i>A sequência da modalidade, sendo resetada a cada ano.</li>
	 *   <li><i>AA: </i>Os ultimos dois digitos do ano.</li>
	 * </ul>
	 *
	 * @param modalidade A modalidade da licença
	 * @return O Protocolo
	 */
	String gerarProtocolo(final Modalidade modalidade) {

		return construirModalidade(modalidade)
			+ DIVISOR_PRIMEIRA_PARTE
			+ construirSequnce(modalidade)
			+ DIVISOR_SEGUNDA_PARTE
			+ construirAno();

	}

	/**
	 * Constrói a string com o prefixo do protocolo.
	 * <p>
	 * LPE ou LPR, sendo Esportiva ou Recreativa
	 *
	 * @param modalidade A modalidade da licença
	 * @return String com a primeira parte do código do protocolo
	 */
	private static String construirModalidade(final Modalidade modalidade) {

		switch (modalidade) {
			case ESPORTIVA:
				return PREFIX_ESPORTIVA;
			case RECREATIVA:
				return PREFIX_RECREATIVA;
			default:
				throw new ProtocoloException("protocolo.modalidade");
		}

	}

	/**
	 * Constrói a segunda parte do protocolo, com o sequencial.
	 * <p>
	 * A sequência é por modalidade, e reseta a cada ano.
	 *
	 * @param modalidade A modalidade da sequência
	 * @return String contendo a sequencia
	 */
	private String construirSequnce(final Modalidade modalidade) {

		return generateSequenceString(incrementOrResetSequence(modalidade));

	}

	/**
	 * Constrói a terceira parte do protocolo, com o ano.
	 * <p>
	 * Sendo apenas os dois últimos dígitos.
	 * Es.: 2018 será apenas 18.
	 *
	 * @return String contendo a sequencia
	 */
	private static String construirAno() {
		var thisYear = DateUtils.getThisYear() + "";

		return thisYear.substring(2);
	}

	/**
	 * Incrementa ou reseta a sequência, validando-a em após isso.
	 *
	 * @param modalidade A modalidade da sequência
	 * @return A sequência
	 */
	private Sequence incrementOrResetSequence(final Modalidade modalidade) {

		var sequence = repository.findByModalidade(modalidade);

		if (DateUtils.getThisYear() != sequence.getYear()) {

			sequence.reset();

		} else {

			sequence.incremente();

		}

		sequence.validate();

		repository.save(sequence);

		return sequence;
	}

	/**
	 * Constrói a string de sequência para o protocolo.
	 *
	 * @param sequence A sequência
	 * @return A String formatada da sequência
	 */
	private static String generateSequenceString(final Sequence sequence) {

		return StringUtils.leftPad(
			sequence.getValorSequence().toString(),
			SEQUENCE_PADDING
		);

	}

}
