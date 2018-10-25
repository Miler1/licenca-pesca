package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.internal;

import br.com.caelum.stella.validation.CPFValidator;
import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;

/**
 * Facade do serviço de Acesso implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class AcessoServiceFacadeImpl implements AcessoServiceFacade {

	private AcessoApplication acessoApplication;

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

		CPFValidator cpfValidator = new CPFValidator();
		PessoaDTOAssembler pessoaDTOAssembler = new PessoaDTOAssembler();

		// CPF e Passaporte não podem ser ambos nulos.
		if (resource.getCpf() == null && resource.getPassaporte() == null)
			throw new ValidationException(
				"Deve existir ao menos um valor"
				+ " de CPF ou Passaporte válidos."
			);

		// Valida CPF.
		if (resource.getPassaporte() != null)
			cpfValidator.assertValid(resource.getCpf());

		// Converte dado Pessoa em DTO de Pessoa
		return pessoaDTOAssembler.toDTO(
			acessoApplication.identificar(resource)
		);

	}
}
