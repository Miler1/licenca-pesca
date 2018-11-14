package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.utils.AbstractSpecification;
import br.ufla.lemaf.ti.carteirapesca.domain.utils.ValueObject;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Especificação da licença.
 * <p>
 * Descreve a data de criação, a data de ativação
 * e status da licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class LicencaSpecification extends AbstractSpecification<Licenca> implements ValueObject<LicencaSpecification> {

	private Date dataAtivacao;

	private LicencaStatus licencaStatus;

	/**
	 * Contrutor padrão.
	 * <p>
	 * Cria uma especificação da Licença. Essa especificação
	 * é imutável
	 *
	 * @param dataAtivacao  A data da ativação da licença
	 * @param licencaStatus O status da licença
	 */
	public LicencaSpecification(final Date dataAtivacao,
	                            final LicencaStatus licencaStatus) {

		this.dataAtivacao = (Date) dataAtivacao.clone();
		this.licencaStatus = licencaStatus;
	}

	/**
	 * @return Data de ativação da licença
	 */
	public Date dataAtivacao() {
		return new Date(dataAtivacao.getTime());
	}

	/**
	 * @return O status da licença
	 */
	public LicencaStatus status() {
		return licencaStatus;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param licenca
	 */
	@Override
	public boolean isSatisfiedBy(Licenca licenca) {
		throw new NotImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameValueAs(LicencaSpecification other) {
		return other != null && new EqualsBuilder()
			.append(this.dataAtivacao, other.dataAtivacao)
			.append(this.licencaStatus, other.licencaStatus)
			.isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.dataAtivacao)
			.append(this.licencaStatus)
			.toHashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		final LicencaSpecification that = (LicencaSpecification) obj;

		return sameValueAs(that);
	}
}
