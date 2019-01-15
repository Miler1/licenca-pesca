package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
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
@Getter
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "licenca")
public class Licenca implements Entity<Licenca, Protocolo> {

	// Anos para a licença vencer após ativada
	private static final Integer ANOS_VENCIMENTO_LICENCA = 1;
	private static final Integer MES_ANTES_DE_VENCER = -1;

	@Embedded
	@AttributeOverride(name = "codigoFormatado", column = @Column(name = "tx_protocolo"))
	private Protocolo protocolo;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "id_modalidade")
	private Modalidade modalidade;

	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;

	@Column(name = "id_status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Column(name = "dt_ativacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAtivacao;

	@Column(name = "tx_caminho_boleto")
	private String caminhoBoleto;

	@Column(name = "tx_caminho_carteira")
	private String caminhoCarteira;

	@ManyToOne
	@JoinColumn(name="id_solicitante")
	private Solicitante solicitante;

	@Column(name = "dt_vencimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;



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
	 * @param caminhoBoleto O caminho do arquivo de boleto
	 */
	public Licenca(final Protocolo protocolo,
	               final Modalidade modalidade,
	               final String caminhoCarteira,
	               final String caminhoBoleto) {
		try {
			Validate.notNull(protocolo);
			Validate.notNull(modalidade);
			Validate.notBlank(caminhoCarteira);
			Validate.notBlank(caminhoBoleto);

			this.protocolo = protocolo;
			this.modalidade = modalidade;
			this.dataCriacao = new Date();
			this.status = Status.AGUARDANDO_PAGAMENTO_BOLETO;
			this.caminhoCarteira = caminhoCarteira;
			this.caminhoBoleto = caminhoBoleto;
		} catch (IllegalArgumentException | NullPointerException ex) {

			throw new LicencaException("licenca.creation");
		}
	}

	/**
	 * Ativa Licença. Caso o Status seja AGUARDANDO será ativado.
	 * Mas se o Status for ATIVO ou INVALIDADO a operação será anulada.
	 */
	public void ativar() {
		if (!status.sameValueAs(Status.AGUARDANDO_PAGAMENTO_BOLETO)) {
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
	public Date getDataVencimento() {

		return dataVencimento;
	}

	public Boolean getPodeRenovar() {
		if (!status.sameValueAs(Status.ATIVO) && !status.sameValueAs(Status.VENCIDO)) {
			return false;
		}
		var vencimento = this.getDataVencimento();
		if(vencimento != null) {
			var dataInicioRenovacao = new GregorianCalendar();
			dataInicioRenovacao.setTime((Date) vencimento.clone());
			dataInicioRenovacao.add(Calendar.MONTH, MES_ANTES_DE_VENCER);

			var dataAtual = new Date();
			return dataAtual.after(dataInicioRenovacao.getTime());
		}

		return false;
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
	 * Gets caminho carteira.
	 *
	 * @return the caminho carteira
	 */
	public String getCaminhoCarteira() {
		return caminhoCarteira;
	}

	public Solicitante getSolicitante() {
		return solicitante;
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

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public Date getDataAtivacao() {
		return dataAtivacao;
	}


	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public void setCaminhoBoleto(String caminhoBoleto) {
		this.caminhoBoleto = caminhoBoleto;
	}

	public void setCaminhoCarteira(String caminhoCarteira) {
		this.caminhoCarteira = caminhoCarteira;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
}
