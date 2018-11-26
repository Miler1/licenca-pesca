package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.utils.ValueObjectBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

import static br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade.*;

/**
 * Value Object do Protocolo da Licensça de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public final class Protocolo extends ValueObjectBase<Protocolo> {

	private String codigo;

	/**
	 * Padrão do protocolo da Licença: LPX-9999/99 .
	 * <p>
	 * No qual, X significa a letra E ou R e
	 * 9 significa número.
	 * <p>
	 * {@see http://gitlab.ti.lemaf.ufla.br/ipaam/
	 * carteira-de-pesca/wikis/licenca-pesca#1-cadastrar-licen%C3%A7a-de-pesca}
	 */
	private static final Pattern VALID_PATTERN = Pattern.compile(
		"LP([ER])-([\\d]{4})/([\\d]{2})"
	);

	/**
	 * Construtor do Protocolo de Licença.
	 * <p>
	 * Licença de Pesca Esportiva: LPE-XXXX/AA
	 * LPE = Licença de Pesca Esportiva
	 * XXXX = código gerado automaticamente pelo sistema
	 * AA = ano
	 * <b>Ex.:</b> LPE-0001/18
	 * <p>
	 * Licença de Pesca Recreativa: LPR-XXXX/AA
	 * LPR = Licença de Pesca Recreativa
	 * XXXX = código gerado automaticamente pelo sistema
	 * AA = ano
	 * <b>Ex.:</b> LPR-0001/18
	 *
	 * @param modalidade O código do protocolo.
	 */
	public Protocolo(Modalidade modalidade) {
		try {
			val factory = new ProtocoloBuilder();
			val protocolo = factory.gerarProtocolo(modalidade);

			Validate.notNull(protocolo);
			Validate.isTrue(VALID_PATTERN.matcher(protocolo).matches());

			this.codigo = protocolo;

		} catch (NullPointerException | IllegalArgumentException ex) {

			throw new ProtocoloException("protocolo.invalido", codigo);

		}

	}

	/**
	 * Busca a modalidade do protoco.
	 *
	 * @return A modalidade do protoco
	 */
	public Modalidade modalidade() {
		switch (VALID_PATTERN.matcher(codigo).group(1)) {
			case "E":
				return ESPORTIVA;
			case "R":
				return RECREATIVA;
			default:
				return UNKNOWN;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return codigo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
