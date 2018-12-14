package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.NoArgsConstructor;
import lombok.var;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Uma Licença de Pesca.
 * <p>
 * Classe central da Domain model, e raiz da agregação.
 * <p>
 * Uma licença tem como identificador único o {@link Protocolo},
 * possui um status de condição, {@link Status}.
 */
@NoArgsConstructor
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "licenca")
public class Licenca implements Entity<Licenca, Protocolo> {

	// Anos para a licença vencer após ativada
	private static final Integer ANOS_VENCIMENTO_LICENCA = 1;

	@Embedded
	@AttributeOverride(name = "codigoFormatado", column = @Column(name = "tx_protocolo"))
	private Protocolo protocolo;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "id_modalidade")
	private Modalidade modalidade;

	@Column(name = "dt_criacao")
	private Date dataCriacao;

	@Column(name = "id_status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Column(name = "dt_ativacao")
	private Date dataAtivacao;

	@Column(name = "tx_caminho_boleto")
	private String caminhoBoleto;

	@Column(name = "tx_caminho_carteira")
	private String caminhoCarteira;

	/**
	 * Construtor da Licenca de pesca.
	 * <p>
	 * O protocolo, o solicitante, e a modalidade não podem ser nulos,
	 * e o representante da modalidade de protocolo deve ser da mesma
	 * modalidade da licença.
	 *
	 * @param protocolo  O número do protocolo
	 * @param modalidade A modalidade da Licença
	 * @param caminhoCarteira O caminho do arquivo da carteira de pesca
	 */
	public Licenca(final Protocolo protocolo,
	               final Modalidade modalidade,
	               final String caminhoCarteira) {
		try {
			Validate.notNull(protocolo);
			Validate.notNull(modalidade);
			Validate.notBlank(caminhoCarteira);

			this.protocolo = protocolo;
			this.modalidade = modalidade;
			this.dataCriacao = new Date();
			this.status = Status.AGUARDANDO;
			this.caminhoCarteira = caminhoCarteira;
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
			throw new LicencaException("licenca.statusInvalido.ativar", status.name());
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
	 * Data vencimento date.
	 *
	 * @return Data de vencimento da Licença
	 */
	public Date dataVencimento() {
		if (!status.sameValueAs(Status.ATIVO)) {
			throw new LicencaException("licenca.statusInvalido.dataVencimento", status.name());
		}
		var vencimento = new GregorianCalendar();
		// Não referenciar, já que Date é um objeto mutável,
		// e vamos alterar o valor de vencimento, mas não
		// queremos alterar o valor de ativação.
		vencimento.setTime((Date) dataAtivacao.clone());
		vencimento.add(Calendar.YEAR, ANOS_VENCIMENTO_LICENCA);
		return vencimento.getTime();
	}

	/**
	 * Protocolo protocolo.
	 *
	 * @return O Protocolo da licença
	 */
	public Protocolo protocolo() {
		return protocolo;
	}

	/**
	 * Modalidade modalidade.
	 *
	 * @return A modalidade da licença
	 */
	public Modalidade modalidade() {
		return modalidade;
	}

	/**
	 * Data criacao date.
	 *
	 * @return A data de criação da licença
	 */
	public Date dataCriacao() {
		return (Date) dataCriacao.clone();
	}

	/**
	 * Gets caminho boleto.
	 *
	 * @return the caminho boleto
	 */
	public String getCaminhoBoleto() {
		return caminhoBoleto;
	}

	/**
	 * Sets caminho boleto.
	 *
	 * @param caminhoBoleto the caminho boleto
	 */
	public void setCaminhoBoleto(String caminhoBoleto) {
		this.caminhoBoleto = caminhoBoleto;
	}

	/**
	 * Gets caminho carteira.
	 *
	 * @return the caminho carteira
	 */
	public String getCaminhoCarteira() {
		return caminhoCarteira;
	}

	/**
	 * Data ativacao date.
	 *
	 * @return Data de ativação da licença
	 */
	public Date dataAtivacao() {
		return br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate.isNull(dataAtivacao)
			? null
			: (Date) dataAtivacao.clone();
	}

	/**
	 * Status status.
	 *
	 * @return O status da licença
	 */
	public Status status() {
		return status;
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
	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
