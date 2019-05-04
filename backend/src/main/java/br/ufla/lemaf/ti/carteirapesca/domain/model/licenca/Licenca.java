package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Convenio;
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
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
	private static final Integer MES_ANTES_DE_VENCER = 1;
	private static final Integer MES_VENCER_CARTEIRA_PROVISORIA = 1;

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Setter
	@Getter
	@Embedded
	@AttributeOverride(name = "codigoFormatado", column = @Column(name = "tx_protocolo"))
	private Protocolo protocolo;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="id_modalidade")
	private Modalidade modalidade;

	@Getter
	@Setter
	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

	@Getter
	@Setter
	@Column(name = "dt_ativacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAtivacao;

	@Column(name = "tx_caminho_carteira")
	private String caminhoCarteira;

	@Setter
	@ManyToOne
	@JoinColumn(name="id_solicitante")
	private Solicitante solicitante;

	@Getter
	@Column(name = "dt_vencimento")
	private LocalDate dataVencimento;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_informacao_complementar")
	private InformacaoComplementar informacaoComplementar;

	@Setter
	@Getter
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_titulo")
	private Titulo titulo;

	@Setter
	@Getter
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_convenio")
	private Convenio convenio;

	public Licenca(final Protocolo protocolo,
				   final Modalidade modalidade,
				   final InformacaoComplementar informacaoComplementar,
				   final Status status,
				   final Titulo titulo,
				   final Convenio convenio) {

			this.protocolo = protocolo;
			this.modalidade = modalidade;
			this.dataCriacao = new Date();
			this.status = status;
			this.informacaoComplementar = informacaoComplementar;
			this.titulo = titulo;
			this.convenio = convenio;

	}

	public InformacaoComplementar getInformacaoComplementar() {
		return informacaoComplementar;
	}

	public void setDataVencimento(LocalDate dataPagamentoTaxa) {

		Long qtdDiasVencimentoCarteira = 365l;

		this.dataVencimento = dataPagamentoTaxa.plusDays(qtdDiasVencimentoCarteira);

	}


	public Boolean getPodeRenovar() {

		if(this.getStatus().getId() == Status.StatusEnum.ATIVO.id){

			var vencimento = this.getDataVencimento();

			if(vencimento != null) {
				return LocalDate.now().isAfter(vencimento.minusMonths(MES_ANTES_DE_VENCER));
			}

			return false;

		}else if(this.getStatus().getId() == Status.StatusEnum.VENCIDO.id && !this.solicitante.pussuiLicencaAtiva(this.modalidade)){
			return true;
		}

		return false;

	}

	public String mensagensDeAviso() {

		var mensagemDeAcordoSituacao = this.getStatus();

		if(mensagemDeAcordoSituacao.getId().equals(Status.StatusEnum.ATIVO_AGUARDANDO_PAGAMENTO.id)){
			return "VÁLIDO EM TODO O ESTADO DO AMAZONAS, MEDIANTE APRESENTAÇÃO DE DOCUMENTO DE IDENTIDADE E COMPROVANTE DE PAGAMENTO, RESPEITANDO AS REGRAS DE PESCA DO LOCAL";
		} else{
			return "VÁLIDO EM TODO O ESTADO DO AMAZONAS, MEDIANTE APRESENTAÇÃO DE DOCUMENTO DE IDENTIDADE, RESPEITANDO AS REGRAS DE PESCA DO LOCAL";
		}
	}

	public String descricaoCarteiraDefinitivaEProvisoria() {

		var mensagemDeAcordoSituacao = this.getStatus();

		if(mensagemDeAcordoSituacao.getId().equals(Status.StatusEnum.ATIVO_AGUARDANDO_PAGAMENTO.id)){
			return "LICENÇA PROVISÓRIA PARA PESCA AMADORA ESTADO DO AMAZONAS";
		} else{
			return "LICENÇA PARA PESCA AMADORA ESTADO DO AMAZONAS";
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

}
