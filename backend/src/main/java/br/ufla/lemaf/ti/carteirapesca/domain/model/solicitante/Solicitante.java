package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaEUDTO;
import lombok.NoArgsConstructor;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.FiltroPessoa;

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
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "solicitante")
public class Solicitante implements Entity<Solicitante, SolicitanteId> {

	private static final int NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE = 3;
	private static final int HORAS_BLOQUEIO_SOLICITANTE = 2;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "cpf.numero", column = @Column(name = "nu_cpf")),
		@AttributeOverride(name = "passaporte.numero", column = @Column(name = "nu_passaporte"))
	})
	private SolicitanteId identity;

	@JoinColumn(name = "id_solicitante")
	@OrderBy(value="dataCriacao")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Licenca> licencas = new ArrayList<>();

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

		return licencas
			.stream()
			.anyMatch(licenca -> (licenca.status().sameValueAs(Status.ATIVO) || licenca.status().sameValueAs(Status.AGUARDANDO_PAGAMENTO_BOLETO)) && licenca.modalidade().sameValueAs(modalidade));
	}

	/**
	 * Adiciona uma licença ao solicitante se não
	 * houver uma liceça ativa.
	 *
	 * @param licenca A licença
	 * @return O protocolo da licença
	 */
	public Protocolo adicionarLicenca(Licenca licenca) {
		if (!pussuiLicencaAtiva(licenca.modalidade())) {
			licencas.add(licenca);
			return licenca.protocolo();
		} else {
			return null;
		}
	}

	/**
	 * @return Todas as Licencas do Solicitante
	 */
	public List<Licenca> buscarTodasLicencas() {
		return licencas;
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
//
//	public static Solicitante buscaSolicitante(String cpf) {
//
//	}
//
//
//	public static void desbloqueiaSolicitante(String cpf){
//
//		Solicitante solicitante = buscaSolicitante(cpf);
//
//		if(solicitante == null){
//			solicitante = new Solicitante();
//		}
//		solicitante.numeroTentativas = 0;
//		solicitante.dataUltimaTentativa = null;
//		solicitante.dataDesbloqueio = null;
//		solicitante.buscarIdentificadorUnico();
//
//	}
//
//	public static Boolean solicitanteBloqueado(String cpfCnpj) {
//
//		Solicitante solicitante = buscaSolicitante(cpfCnpj);
//
//		if(solicitante != null && solicitante.dataDesbloqueio != null) {
//
//			if(DateUtils.dataMaiorQue(new Date(), solicitante.dataDesbloqueio)) {
//				desbloqueiaSolicitante(cpfCnpj);
//				return false;
//			}
//
//			return true;
//
//		} else if(solicitante != null && solicitante.dataUltimaTentativa != null && solicitante.numeroTentativas < NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE) {
//
//			Date dataUltimaTentativa = DateUtils.somarHorasData(solicitante.dataUltimaTentativa, 24);
//
//			if(DateUtils.dataMaiorQue(new Date(), dataUltimaTentativa)) {
//				desbloqueiaUsuario(cpfCnpj);
//				return false;
//			}
//
//		}
//
//		return false;
//
//	}
//
//	public static void atualizaNumeroTentativas(String cpfCnpj) {
//
//		Solicitante solicitante = buscaSolicitante(cpfCnpj);
//
//		if(solicitante != null) {
//
//			solicitante.numeroTentativas = solicitante.numeroTentativas + 1;
//
//			if(solicitante.numeroTentativas == NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE) {
//				solicitante.dataDesbloqueio = DateUtils.somarHorasData(new Date(), HORAS_BLOQUEIO_SOLICITANTE);
//			}
//
//		} else {
//
//			solicitante = new Solicitante();
//
//			solicitante.() = CpfCnpjUtil.removerMascara(cpfCnpj);
//			solicitante.numeroTentativas = 1;
//
//		}
//
//		solicitante.dataUltimaTentativa = new Date();
//
//		solicitante.validateAndSave();
//
//		em().getTransaction().commit();
//		em().getTransaction().begin();
//
//	}
}
