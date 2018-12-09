package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import java.util.regex.Pattern;

import static br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade.*;

/**
 * Value Object do Protocolo da Licensça de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@Entity
@NoArgsConstructor
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "protocolo")
public final class Protocolo extends ValueObjectBase<Protocolo> {

	@Column(name = "val_codigo")
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
	 * @param protocolo O código do protocolo.
	 */
	public Protocolo(String protocolo) {

		try {

			Validate.notNull(protocolo);
			Validate.isTrue(VALID_PATTERN.matcher(protocolo).matches());

			this.codigo = protocolo;

		} catch (NullPointerException | IllegalArgumentException ex) {

			throw new ProtocoloException("protocolo.invalido", protocolo);

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

	// Surrugate key para o Hibernate
	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

}
