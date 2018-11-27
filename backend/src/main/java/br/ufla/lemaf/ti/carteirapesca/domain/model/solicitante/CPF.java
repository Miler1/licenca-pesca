package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObjectBase;
import lombok.val;

/**
 * Representa um Cadastro de Pessoa Física - CPF.
 * <p>
 * Baseado no CPF da Stella Caelum
 * {@see https://github.com/caelum/caelum-stella/blob/master/
 * stella-core/src/main/java/br/com/caelum/stella/tinytype/CPF.java}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class CPF extends ValueObjectBase<CPF> {

	private final String numero;

	private final String numeroFormatado;

	/**
	 * Constrói um CPF com o número especificado. Se o número contiver
	 * apenas caracteres numéricos ou estiver no formato ddd.ddd.ddd-dd,
	 * ele é guardado com e sem formatação nos respectivos atributos.
	 * Caso contrário, ele guarda o parâmetro como passado em ambos os atributos.
	 *
	 * @param numero O número do CPF
	 */
	public CPF(final String numero) {
		val formatter = new CPFFormatter();

		if (formatter.isFormatted(numero)) {

			this.numero = formatter.unformat(numero);
			this.numeroFormatado = numero;

		} else if (formatter.canBeFormatted(numero)) {

			this.numero = numero;
			this.numeroFormatado = formatter.format(numero);

		} else {

			this.numero = numero;
			this.numeroFormatado = numero;

		}
	}

	/**
	 * Retorna o número do CPF apenas com os caracteres numéricos.
	 *
	 * @return número do CPF.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Retorna o número do CPF no formato ddd.ddd.ddd-dd .
	 *
	 * @return número do CPF no formato ddd.ddd.ddd-dd .
	 */
	public String getNumeroFormatado() {
		return numeroFormatado;
	}

	/**
	 * Retorna se o número do CPF é válido. O resultado é <code>true</code>
	 * se os dígitos verificadores estão de acordo com a regra de cálculo.
	 *
	 * @return se o número do CPF é valido.
	 * @see CPFValidator
	 */
	public boolean isValid() {
		return new CPFValidator().invalidMessagesFor(numero).isEmpty();
	}

	/**
	 * Retorna uma representação em string desse CPF. A intenção desse método
	 * é ser usado para impressão e retorna o número no formato ddd.ddd.ddd-dd .
	 *
	 * @return número do CPF no formato ddd.ddd.ddd-dd
	 */
	@Override
	public String toString() {
		return getNumeroFormatado();
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
