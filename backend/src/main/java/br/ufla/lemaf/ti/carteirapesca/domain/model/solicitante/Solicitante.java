package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CarteiraUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Gerador;
import lombok.NoArgsConstructor;
import lombok.val;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public boolean pussuiLicencaAtiva() {
		return licencas
			.stream()
			.anyMatch(licenca -> licenca.status().sameValueAs(Status.ATIVO));
	}

	/**
	 * Adiciona uma licença ao solicitante se não
	 * houver uma liceça ativa.
	 *
	 * @param licenca A licença
	 * @return O protocolo da licença
	 */
	public Protocolo adicionarLicenca(Licenca licenca) {
		if (!pussuiLicencaAtiva()) {
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

	private static Map<String, Object[]> preencherListaVerificacao(Pessoa pessoa) {

		Map<String, Object[]> listasVerificacao = new HashMap<>();

		Integer qtdCaracteresCpfCnpj = pessoa.cpf.length();

		if(qtdCaracteresCpfCnpj == 11) {
			preencherListaVerificacaoPessoa(listasVerificacao, pessoa);
		}

		return listasVerificacao;

	}
	/**
	 * Gerar nomes da mãe
	 *
	 */

	private static void preencherListaVerificacaoPessoa(Map<String, Object[]> listasVerificacao, Pessoa pessoa) {

		Gerador gerador = new Gerador();

		Integer quantidade = 5;
		Integer padrao = Integer.valueOf(pessoa.cpf.substring(pessoa.cpf.length()-1));
		Integer posicao = padrao > 3 ? Math.abs(padrao/3) : padrao;


		String[] maes = gerador.gerarMaes(quantidade, padrao);

		if(posicao > 3) {
			posicao = 0;
		}

		maes[posicao++] = CarteiraUtils.capitalize(pessoa.nomeMae);
		listasVerificacao.put("maes", maes);

	}

}
