package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.ProtocoloException;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Sequence;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.SequenceRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProtocoloBuilderImpl implements ProtocoloBuilder {

	private SequenceRepository repository;

	private static final Integer SEQUENCE_PADDING = 4;

	private static final String DIVISOR_PRIMEIRA_PARTE = "-";

	private static final String DIVISOR_SEGUNDA_PARTE = "/";

	private static final String PREFIX_ESPORTIVA = "LPE";

	private static final String PREFIX_RECREATIVA = "LPR";

	@Autowired
	public ProtocoloBuilderImpl(SequenceRepository sequenceRepository) {
		this.repository = sequenceRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String gerarProtocolo(Modalidade modalidade) {
		return construirEtapaModalidade(modalidade)
			+ DIVISOR_PRIMEIRA_PARTE
			+ construirEtapaSequence(modalidade)
			+ DIVISOR_SEGUNDA_PARTE
			+ construirEtapaAno();
	}
	/**
	 * Constrói a string com o prefixo do protocolo.
	 * <p>
	 * LPE ou LPR, sendo Esportiva ou Recreativa
	 *
	 * @param modalidade A modalidade da licença
	 * @return String com a primeira parte do código do protocolo
	 */
	private static String construirEtapaModalidade(final Modalidade modalidade) {

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
	private String construirEtapaSequence(final Modalidade modalidade) {

		return generateSequenceString(incrementOrResetSequence(modalidade));

	}

	/**
	 * Constrói a terceira parte do protocolo, com o ano.
	 * <p>
	 * Sendo apenas os dois últimos dígitos.
	 * Ex.: 2018 será apenas 18.
	 *
	 * @return String contendo os dois últimos dígitos do ano
	 */
	private static String construirEtapaAno() {
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
			sequence.getValor().toString(),
			SEQUENCE_PADDING,
			"0"
		);

	}
}
