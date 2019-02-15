package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.EnderecoEstrangeiro;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Value Object de Solicitante, ou seja, a pessoa que solicitou
 * {@link Licenca}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "solicitante")
public class Solicitante implements Entity<Solicitante, SolicitanteId> {

	@Transient
	@Autowired
	private StatusRepository statusRepository;

	private static AcessoServiceFacade acessoServiceFacade;

	private static SolicitanteRopository solicitanteRopository;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "cpf.numero", column = @Column(name = "nu_cpf")),
		@AttributeOverride(name = "passaporte.numero", column = @Column(name = "nu_passaporte"))
	})
	private SolicitanteId identity;

	@JoinColumn(name = "id_solicitante")
	@OrderBy(value="dataCriacao")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Licenca> licenca = new ArrayList<>();

	@Column(name = "numero_tentativas", insertable = false)
	private Integer numeroTentativas;

	@Column(name = "data_ultima_tentativa")
	private Date dataUltimaTentativa;

	@Column(name = "data_desbloqueio")
	private Date dataDesbloqueio;

	@Setter
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_endereco_estrangeiro")
	private EnderecoEstrangeiro enderecoEstrangeiro;

	/**
	 * Construtor de solicitante.
	 *
	 * @param cpf        O CPF do solicitante
	 * @param passaporte O passaporte do Solicitante
	 */
	public Solicitante(final CPF cpf, final Passaporte passaporte) {
		val factory = new SolicitanteIdFactory();
		this.identity = factory.gerarSolicitanteId(cpf, passaporte);
	}

	/**
	 * Busca uma String com um unico
	 * identificador do solicitante.
	 *
	 * @return O identificador em string
	 */
	public String buscarIdentificadorUnico() {
		return identity.valor();
	}

	/**
	 * Busca se o Solicitante possui alguma licença ativa.
	 *
	 * @return {@code true} se alguma licença estiver ativa
	 */
	public boolean pussuiLicencaAtiva(Modalidade modalidade) {
		return this.licenca
			.stream()
			.anyMatch(licencaProcurada -> ((licencaProcurada.getStatus().getId().equals(Status.StatusEnum.ATIVO.id)) ||
				licencaProcurada.getStatus().getId().equals(Status.StatusEnum.AGUARDANDO_PAGAMENTO.id) ||
				licencaProcurada.getStatus().getId().equals(Status.StatusEnum.ATIVO_AGUARDANDO_PAGAMENTO.id))
				&& licencaProcurada.modalidade().getId().equals(modalidade.getId()));
	}

	/**
	 * Adiciona uma licença ao solicitante se não
	 * houver uma liceça ativa.
	 *
	 * @param licencaAdd A licença
	 * @return O protocolo da licença
	 */
	public Protocolo adicionarLicenca(Licenca licencaAdd, Boolean renovando) {
		if (renovando || !pussuiLicencaAtiva(licencaAdd.modalidade())) {
			licenca.add(licencaAdd);
			return licencaAdd.protocolo();
		} else {
			return null;
		}
	}

	/**
	 * @return Todas as Licencas do Solicitante
	 */
	public List<Licenca> buscarTodasLicencas() {
		return licenca;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameIdentityAs(final Solicitante other) {
		return other != null && identity.sameValueAs(other.identity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SolicitanteId identity() {
		return identity;
	}

	// Surrugate key para o Hibernate
	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Factory de SolicitanteId.
	 *
	 * @author Highlander Paiva
	 * @since 1.0
	 */
	static class SolicitanteIdFactory {

		/**
		 * Gera o identificador do solicitante.
		 *
		 * @param cpf        O CPF
		 * @param passaporte O Passaporte
		 * @return O SolicitanteId
		 */
		SolicitanteId gerarSolicitanteId(final CPF cpf, final Passaporte passaporte) {
			return new SolicitanteId(cpf, passaporte);
		}

	}

	public void limpaDadosDesbloqueioSolicitante() throws Exception {

		if(this == null){
			throw new Exception("Não existe solicitante para esse CPF/passaporte");
		}

		this.numeroTentativas = 0;
		this.dataUltimaTentativa = null;
		this.dataDesbloqueio = null;

	}

	public void atualizaNumeroTentativas() throws Exception {

		if(this == null){
			throw new Exception("Não existe solicitante para esse CPF/passaporte");
		}

		this.numeroTentativas = (this.numeroTentativas == null ? 1 : this.numeroTentativas + 1);

		this.dataUltimaTentativa = new Date();

		if(this.numeroTentativas == Constants.NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE) {

			this.dataDesbloqueio = DateUtils.somarHorasData(new Date(), Constants.HORAS_BLOQUEIO_SOLICITANTE);
		}

	}
}
