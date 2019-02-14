package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	private static StatusRepository statusRepository;

	// Anos para a licença vencer após ativada
	private static final Integer ANOS_VENCIMENTO_LICENCA = 1;
	private static final Integer MES_ANTES_DE_VENCER = -1;
	private static final Integer QTD_MESES_VENCIMENTO_BOLETO_APOS_EMISSAO = 1;
	private static final Integer MES_VENCER_CARTEIRA_PROVISORIA = 1;

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Embedded
	@AttributeOverride(name = "codigoFormatado", column = @Column(name = "tx_protocolo"))
	private Protocolo protocolo;

	@ManyToOne
	@JoinColumn(name="id_modalidade")
	private Modalidade modalidade;

	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;

	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

	@Column(name = "dt_ativacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAtivacao;

	@Column(name = "tx_caminho_carteira")
	private String caminhoCarteira;

	@ManyToOne
	@JoinColumn(name="id_solicitante")
	private Solicitante solicitante;

	@Column(name = "dt_vencimento")
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@Column(name = "dt_vencimento_provisoria")
	private Date dataVencimentoProvisoria;

	@Column(name = "dt_vencimento_boleto")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimentoBoleto;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_informacao_complementar")
	private InformacaoComplementar informacaoComplementar;

	@Setter
	@Getter
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_titulo")
	private Titulo titulo;

	public Licenca(final Protocolo protocolo,
				   final Modalidade modalidade,
				   final InformacaoComplementar informacaoComplementar,
				   final Status status,
				   final Titulo titulo) {
		try {
			Validate.notNull(protocolo);
			Validate.notNull(modalidade);

			this.protocolo = protocolo;
			this.modalidade = modalidade;
			this.dataCriacao = new Date();
			this.status = status;
			this.informacaoComplementar = informacaoComplementar;
			this.titulo = titulo;
			this.setDataVencimentoProvisoria();
			this.setDataVencimentoBoleto();

		} catch (IllegalArgumentException | NullPointerException ex) {

			throw new LicencaException("licenca.creation");
		}
	}


	/**
	 * Ativa Licença. Caso o Status seja AGUARDANDO será ativado.
	 * Mas se o Status for ATIVO ou INVALIDADO a operação será anulada.
	 */
	public void ativar() {
		if (!status.getId().equals(Status.StatusEnum.AGUARDANDO_PAGAMENTO.id)) {
			throw new LicencaException("licenca.statusInvalido.ativar", status.getDescricao());
		}
		status = statusRepository.findById(Status.StatusEnum.ATIVO.id).get();
		dataAtivacao = new Date();
	}

	/**
	 * Invalidar uma licença. A mesma já não pode ser inválida.
	 */
	public void invalidar() {
		if (status.getId().equals(statusRepository.findById(Status.StatusEnum.INVALIDADO.id).get().getId())) {
			throw new LicencaException("licenca.statusInvalido.invalidar");
		}
		status = statusRepository.findById(Status.StatusEnum.INVALIDADO.id).get();
	}

	/**
	 * Data vencimento date.
	 *
	 * @return Data de vencimento da Licença
	 */
	public Date getDataVencimento() {

		return dataVencimento;
	}

	/**
	 * @return Data de vencimento da Licença provisoria
	 */
	public Date getDataVencimentoProvisoria() {
		return dataVencimentoProvisoria;
	}

	public InformacaoComplementar getInformacaoComplementar() {
		return informacaoComplementar;
	}

	public void setDataVencimentoBoleto() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, QTD_MESES_VENCIMENTO_BOLETO_APOS_EMISSAO);

		this.dataVencimentoBoleto = calendar.getTime();

	}

	public void setDataVencimentoProvisoria() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, MES_VENCER_CARTEIRA_PROVISORIA);

		this.dataVencimentoProvisoria = calendar.getTime();
	}

	public Boolean getPodeRenovar() {
		if (!status.getId().equals(Status.StatusEnum.ATIVO.id) && !status.getId().equals(Status.StatusEnum.VENCIDO.id)) {
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

	public String mensagensDeAviso() {

		var vencimento = this.getDataVencimento();

		if(vencimento == null){
			return "VÁLIDO EM TODO O ESTADO DO AMAZONAS, MEDIANTE APRESENTAÇÃO DE DOCUMENTO DE IDENTIDADE E COMPROVANTE DE PAGAMENTO, RESPEITANDO AS REGRAS DE PESCA DO LOCAL";
		} else{
			return "VÁLIDO EM TODO O ESTADO DO AMAZONAS, MEDIANTE APRESENTAÇÃO DE DOCUMENTO DE IDENTIDADE, RESPEITANDO AS REGRAS DE PESCA DO LOCAL";
		}
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
	 * Gets caminho carteira.
	 *
	 * @return the caminho carteira
	 */
	public String getCaminhoCarteira() {
		return caminhoCarteira;
	}

	public Solicitante solicitante() {
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

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
}
