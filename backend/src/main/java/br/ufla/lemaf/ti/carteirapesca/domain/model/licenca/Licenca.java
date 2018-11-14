package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.utils.Entity;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Uma Licença de Pesca.
 * <p>
 * Classe central da Domain model, e raiz da agregação.
 * <p>
 * Uma licença tem como identificador único o {@link Protocolo},
 * possui um status de condição, {@link Status}, e um
 * {@link Solicitante}.
 */
@NoArgsConstructor
public class Licenca implements Entity<Licenca, Protocolo> {

	// Anos para a licença vencer após ativada
	private static final Integer VENCIMENTO_LICENCA = 1;

	private Protocolo protocolo;

	private Solicitante solicitante;

	private Modalidade modalidade;

	private Date dataCriacao;

	private Status status;

	private Date dataAtivacao;

	/**
	 * Construtor da Licenca de pesca.
	 * <p>
	 * O protocolo, o solicitante, e a modalidade não podem ser nulos,
	 * e o representante da modalidade de protocolo deve ser da mesma
	 * modalidade da licença.
	 *
	 * @param protocolo   O número do protocolo
	 * @param solicitante O Solicitante da licença
	 * @param modalidade  A modalidade da Licença
	 */
	public Licenca(final Protocolo protocolo, final Solicitante solicitante, Modalidade modalidade) {
		try {
			Validate.notNull(protocolo);
			Validate.notNull(solicitante);
			Validate.notNull(modalidade);
			Validate.isTrue(protocolo.modalidade().sameValueAs(modalidade));

			this.protocolo = protocolo;
			this.solicitante = solicitante;
			this.modalidade = modalidade;
			this.dataCriacao = new Date();
			this.status = Status.AGUARDANDO;
		} catch (IllegalArgumentException | NullPointerException ex) {

			throw new LicencaException("licenca.creation");
		}
	}

	/**
	 * Ativa Licença. Caso o Status seja AGUARDANDO será ativado.
	 * Mas se o Status for ATIVO ou INVALIDADO a operação será anulada.
	 */
	public void ativar() {
		if (!status.sameValueAs(Status.AGUARDANDO)) {
			throw new LicencaException("licenca.statusInvalido.ativar");
		}
		status = Status.ATIVO;
		dataAtivacao = new Date();
	}

	/**
	 * Invalidar uma licença. A mesma já não pode ser inválida.
	 */
	public void invalidar() {
		if (status.sameValueAs(Status.INVALIDADO)) {
			throw new LicencaException("licenca.statusInvalido.invalidar");
		}
		status = Status.INVALIDADO;
	}

	/**
	 * @return Data de vencimento da Licença
	 */
	public Date dataVencimento() {
		if (!status.sameValueAs(Status.ATIVO)) {
			throw new LicencaException("licenca.statusInvalido.dataVencimento");
		}
		GregorianCalendar vencimento = new GregorianCalendar();
		// Não referenciar, já que Date é um objeto mutável,
		// e vamos alterar o valor de vencimento, mas não
		// queremos alterar o valor de ativação
		vencimento.setTime((Date) dataAtivacao.clone());
		vencimento.add(Calendar.YEAR, VENCIMENTO_LICENCA);
		return vencimento.getTime();
	}

	/**
	 * @return O Protocolo da licença
	 */
	public Protocolo protocolo() {
		return protocolo;
	}

	/**
	 * @return O Solicitante
	 */
	public Solicitante buscaSolicitante() {
		return solicitante;
	}

	/**
	 * @return A modalidade da licença
	 */
	public Modalidade modalidade() {
		return modalidade;
	}

	/**
	 * @return A data de criação da licença
	 */
	public Date dataCriacao() {
		return (Date) dataCriacao.clone();
	}

	/**
	 * @return Data de ativação da licença
	 */
	public Date dataAtivacao() {
		return br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate.isNull(dataAtivacao)
			? null
			: (Date) dataAtivacao.clone();
	}

	/**
	 * @return O status da licença
	 */
	public Status status() {
		return status;
	}

	/**
	 * @return A licença
	 */
	public Licenca self() {
		if (dataVencimento().after(new Date())
			&& status.sameValueAs(Status.ATIVO)) {
			invalidar();
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameIdentityAs(Licenca other) {
		return other != null && protocolo.sameValueAs(other.protocolo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo identity() {
		return protocolo;
	}

	// --- Calculos internos

	// Surrugate key para o Hibernate
	@SuppressWarnings("unused")
	private Long id;
}
