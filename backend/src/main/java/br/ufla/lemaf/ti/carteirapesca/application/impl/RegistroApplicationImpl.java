package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.application.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.CPF;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Passaporte;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.NotImplementedException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
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

	private static final Integer ESPORTIVA = 0;
	private static final Integer RECREATIVA = 1;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo registrar(final RegistroResource resource) {
		cadastrarPessoa(resource.getPessoa());

		var solicitante = new Solicitante(
			new CPF(resource.getPessoa().getCpf()),
			new Passaporte(resource.getPessoa().getPassaporte())
		);

		var modalidade = gerarModalidade(resource.getInformacaoComplementar().getModalidade());
		var protocolo = new Protocolo(modalidade);
		var licenca = new Licenca(protocolo, modalidade);

		solicitante.adicionarLicenca(licenca);

		return licenca.protocolo();
	}

	/**
	 * Cadastra o usuário no Entrada Única.
	 *
	 * @param pessoa A pessoa
	 */
	private void cadastrarPessoa(PessoaDTO pessoa) {

		// Construir Objeto Pessoa

		WebServiceUtils.validarWebService();

		throw new NotImplementedException();

	}

	/**
	 * Constroi uma modalidade a partir da modalidade.
	 *
	 * @param tipo O tipo da modalidade
	 * @return A Modalidade
	 */
	private Modalidade gerarModalidade(Integer tipo) {

		if (tipo.equals(ESPORTIVA)) {

			return Modalidade.ESPORTIVA;

		} else if (tipo.equals(RECREATIVA)) {

			return Modalidade.RECREATIVA;

		} else {

			return Modalidade.UNKNOWN;

		}

	}
}
