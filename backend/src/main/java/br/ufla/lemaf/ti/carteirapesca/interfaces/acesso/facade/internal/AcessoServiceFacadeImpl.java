package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.SolicitanteRopository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTOAssembler;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade do serviço de Acesso implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class AcessoServiceFacadeImpl implements AcessoServiceFacade {

	private AcessoApplication acessoApplication;

	@Autowired
	private SolicitanteRopository solicitanteRopository;

	/**
	 * Injetando Dependência.
	 *
	 * @param acessoApplication Serviço de Acesso da camada de application.
	 */
	@Autowired
	public AcessoServiceFacadeImpl(AcessoApplication acessoApplication) {
		this.acessoApplication = acessoApplication;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaDTO acessar(final AcessoResource resource) {

		val pessoaDTOAssembler = new PessoaDTOAssembler();
		AcessoResource recursoValidado;

		// CPF e Passaporte não podem ser ambos nulos.
		if (resource.getCpf() == null && resource.getPassaporte() == null)
			throw new ValidationException("acesso.resourceInvalid.cpfAndPassaporte");

		/* Valida e desformata CPF se não
		 * existir Passaporte.
		 * Caso contrario, reinstancia o
		 * recursoValidado com os valores de
		 * resource.
		 */
		if (resource.getCpf() != null) {
			recursoValidado = new AcessoResource(
				CPFUtils.unformat(resource.getCpf()),
				resource.getPassaporte()
			);
		} else {
			recursoValidado = new AcessoResource(
				resource.getCpf(),
				resource.getPassaporte()
			);
		}

		// Converte dado Pessoa em DTO de Pessoa
		return pessoaDTOAssembler.toDTO(
			acessoApplication.identificar(recursoValidado)
		);

	}

	@Override
	public List<Licenca> buscarLicencasPorPessoaDTO(PessoaDTO pessoa) throws Exception {

		Solicitante solicitante;

		if (pessoa.getCpf() != null) {
			solicitante = solicitanteRopository.findByIdentityCpfNumero(pessoa.getCpf());
		} else {
			solicitante = solicitanteRopository.findByIdentityPassaporteNumero(pessoa.getPassaporte());
		}

		if(solicitante == null) {
			throw new Exception("Pessoa não encontrada!");
		}

		return solicitante.buscarTodasLicencas();
	}
}
