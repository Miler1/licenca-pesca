package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.*;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do serviço de registro da camada de aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class RegistroApplicationImpl implements RegistroApplication {

	private static final Integer ESPORTIVA = Modalidade.ESPORTIVA.ordinal();
	private static final Integer RECREATIVA = Modalidade.RECREATIVA.ordinal();

	private ProtocoloBuilder protocoloBuilder;

	private SolicitanteRopository solicitanteRopository;

	/**
	 * Injetando dependências.
	 *
	 * @param protocoloBuilder O Builder de protocolo
	 * @param solicitanteRopository O repositório do solicitante
	 */
	@Autowired
	public RegistroApplicationImpl(final ProtocoloBuilder protocoloBuilder,
	                               final SolicitanteRopository solicitanteRopository) {
		this.protocoloBuilder = protocoloBuilder;
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

		return new Licenca(protocolo, modalidade);
	}

	/**
	 * Cadastra o usuário no Entrada Única.
	 *
	 * @param pessoa A pessoa
	 */
	private void cadastrarPessoa(PessoaDTO pessoa) {

		// TODO Construir Objeto Pessoa

		WebServiceUtils.validarWebService();

		throw new NotImplementedException();

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
			solicitante = solicitanteRopository.findByIdentity_Cpf_Numero(cpf.getNumero());
		} else if (passaporte != null) {
			solicitante = solicitanteRopository.findByIdentity_Passaporte_Numero(passaporte.getNumero());
		}

		if (solicitante == null) {
			solicitante = new Solicitante(cpf, passaporte);
			// cadastrarPessoa(resource.getPessoa());
		}

		return solicitante;
	}
}
