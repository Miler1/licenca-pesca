package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.*;
import br.ufla.lemaf.ti.carteirapesca.domain.services.BoletoBuilder;
import br.ufla.lemaf.ti.carteirapesca.domain.services.CarteiraBuilder;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do serviço de registro da camada de aplicação.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class RegistroApplicationImpl implements RegistroApplication {

	private static final Integer ESPORTIVA = Modalidade.ESPORTIVA.ordinal();
	private static final Integer RECREATIVA = Modalidade.RECREATIVA.ordinal();

	private ProtocoloBuilder protocoloBuilder;
	private CarteiraBuilder carteiraBuilder;
	private BoletoBuilder boletoBuilder;

	private SolicitanteRopository solicitanteRopository;

	/**
	 * Injetando dependências.
	 *
	 * @param protocoloBuilder O Builder de protocolo
	 * @param carteiraBuilder O Builder do arquivo da carteira de pesca
	 * @param boletoBuilder O Builder do boleto
	 * @param solicitanteRopository O repositório do solicitante
	 */
	@Autowired
	public RegistroApplicationImpl(final ProtocoloBuilder protocoloBuilder,
	                               final CarteiraBuilder carteiraBuilder,
	                               final BoletoBuilder boletoBuilder,
	                               final SolicitanteRopository solicitanteRopository) {
		this.protocoloBuilder = protocoloBuilder;
		this.carteiraBuilder = carteiraBuilder;
		this.boletoBuilder = boletoBuilder;
		this.solicitanteRopository = solicitanteRopository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo registrar(final RegistroResource resource) {

		var solicitante = getSolicitante(resource);

		Protocolo protocolo;

		if (!solicitante.pussuiLicencaAtiva()) {

			var licenca = criarLicenca(resource);

			protocolo = solicitante.adicionarLicenca(licenca);

		} else {

			throw new SolicitanteException("solicitante.licenca.ativa");
		}

		solicitanteRopository.save(solicitante);

		return protocolo;
	}

	/**
	 * Cria uma licença de pesca.
	 *
	 * @param resource Os dados de registro.
	 * @return A Licenca
	 */
	private Licenca criarLicenca(final RegistroResource resource) {

		var modalidade = gerarModalidade(resource.getInformacaoComplementar().getModalidade());
		var protocolo = new Protocolo(protocoloBuilder.gerarProtocolo(modalidade));

		var pessoa = buscarDadosSolicitante(getSolicitante(resource));

		var caminhoCarteira = carteiraBuilder.gerarCarteira(protocolo, modalidade, pessoa);
		var caminhoBoleto = boletoBuilder.gerarBoleto(protocolo, modalidade, pessoa);

		return new Licenca(protocolo, modalidade, caminhoCarteira, caminhoBoleto);
	}

	/**
	 * Cadastra o usuário no Entrada Única.
	 *
	 * @param pessoa A pessoa
	 */
	private void cadastrarPessoa(PessoaDTO pessoa) {

		WebServiceUtils.validarWebService();

		var pessoaEU = WebServiceUtils
			.webServiceEU()
			.buscarPessoaFisicaPeloCpf(pessoa.getCpf());

		if (pessoaEU == null) {

			WebServiceUtils
				.webServiceEU()
				.cadastrarPessoa(pessoa);
		}
	}

	/**
	 * Constroi uma modalidade a partir da modalidade.
	 *
	 * @param tipo O tipo da modalidade
	 * @return A Modalidade
	 */
	private static Modalidade gerarModalidade(Integer tipo) {

		if (tipo.equals(ESPORTIVA)) {

			return Modalidade.ESPORTIVA;

		} else if (tipo.equals(RECREATIVA)) {

			return Modalidade.RECREATIVA;

		} else {

			return Modalidade.UNKNOWN;

		}

	}

	/**
	 * Busca o solicitante se o mesmo já estiver registrado ou
	 * cria um novo caso não esteja registrado.
	 *
	 * @param resource Os dados de registro
	 * @return O Solicitante
	 */
	private Solicitante getSolicitante(final RegistroResource resource) {

		var cpf = !Validate.isNull(resource.getPessoa().getCpf())
			? new CPF(resource.getPessoa().getCpf())
			: null;
		var passaporte = !Validate.isNull(resource.getPessoa().getPassaporte())
			? new Passaporte(resource.getPessoa().getPassaporte())
			: null;

		Solicitante solicitante = null;

		if (cpf != null) {
			solicitante = solicitanteRopository.findByIdentityCpfNumero(cpf.getNumero());
		} else if (passaporte != null) {
			solicitante = solicitanteRopository.findByIdentityPassaporteNumero(passaporte.getNumero());
		}

		if (solicitante == null) {
			solicitante = new Solicitante(cpf, passaporte);
			cadastrarPessoa(resource.getPessoa());
		}

		return solicitante;
	}

	/**
	 * Busca os dados do solicitante no Entrada Unica.
	 *
	 * @param solicitante O Solicitante
	 * @return A Pessoa: Dados de solicitante no EU
	 */
	private static Pessoa buscarDadosSolicitante(Solicitante solicitante) {
		var identificador = solicitante.identity();

		if (identificador.isPassaporte())
			throw new NotImplementedException();

		return WebServiceUtils
			.webServiceEU()
			.buscarPessoaFisicaPeloCpf(identificador.valor());
	}
}
