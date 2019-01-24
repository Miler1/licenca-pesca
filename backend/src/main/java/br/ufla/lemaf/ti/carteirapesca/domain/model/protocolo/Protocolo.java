package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloFormatter;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;

import javax.persistence.*;

import static br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade.*;

/**
 * Value Object do Protocolo da Licensça de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Getter
@Embeddable
@NoArgsConstructor
public final class Protocolo extends ValueObjectBase<Protocolo> {

	@Column(name = "tx_protocolo")
	private String codigoFormatado;

	@Transient
	private String codigo;

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
		val formatter = new ProtocoloFormatter();

		if (formatter.isFormatted(protocolo)) {

			this.codigo = formatter.unformat(protocolo);
			this.codigoFormatado = protocolo;

		} else if (formatter.canBeFormatted(protocolo)) {

			this.codigo = protocolo;
			this.codigoFormatado = formatter.format(protocolo);

		} else {

			this.codigoFormatado = protocolo;
			this.codigo = protocolo;

		}

	}

	/**
	 * Busca a modalidade do protoco.
	 *
	 * @return A modalidade do protoco
	 */
	public Modalidades modalidade() {
		switch (ProtocoloValidator.FORMATED.matcher(codigoFormatado).group(1)) {
			case "LPE":
				return Modalidades.PESCA_ESPORTIVA;
			case "LPR":
				return Modalidades.PESCA_REACREATIVA;
			default:
				return null;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return codigoFormatado;
	}

	/**
	 * Busca o código do protocolo sem formatação.
	 *
	 * @return O Protocolo sem formatação
	 */
	public String getProtocoloNaoFormatado() {
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
