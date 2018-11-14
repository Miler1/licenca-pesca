package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.utils.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

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

	private static final Pattern VALID_PATTERN = Pattern.compile(
		"LP[ER]-[\\d]{4}/[\\d]{2}"
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
	 * @param codigo O código do protocolo.
	 */
	public Protocolo(String codigo) {
		try {

			Validate.notNull(codigo);
			Validate.isTrue(VALID_PATTERN.matcher(codigo.toUpperCase()).matches());

			this.codigo = codigo.toUpperCase();

		} catch (NullPointerException | IllegalArgumentException ex) {

			throw new ValidationException("model.protocoloInvalido", codigo);

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return codigo;
	}

}
