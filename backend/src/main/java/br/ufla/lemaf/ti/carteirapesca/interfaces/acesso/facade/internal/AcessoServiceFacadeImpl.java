package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.internal;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa.PessoaDTOAssembler;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

/**
 * Facade do serviço de Acesso implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
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
		AcessoResource recursoValidado;

		// CPF e Passaporte não podem ser ambos nulos.
		if (resource.getCpf() == null && resource.getPassaporte() == null)
			throw new ValidationException(
				"Deve existir ao menos um valor"
					+ " de CPF ou Passaporte válidos."
			);

		// Valida e desformata CPF se não
		// existir Passaporte.
		// Caso contrario, reinstancia o
		// recursoValidado com os valores de
		// resource.
		if (resource.getCpf() != null)
			recursoValidado = new AcessoResource(
				cpfFacade(resource.getCpf()),
				resource.getPassaporte()
			);
		else recursoValidado = new AcessoResource(
			resource.getCpf(),
			resource.getPassaporte()
		);

		// Converte dado Pessoa em DTO de Pessoa
		return pessoaDTOAssembler.toDTO(
			acessoApplication.identificar(recursoValidado)
		);

	}

	/**
	 * Converte CPF no formato usado pela aplicação.
	 *
	 * @param cpf O CPF de acesso
	 * @return O CPF válidado e desformatado
	 */
	private static String cpfFacade(final String cpf) {

		CPFFormatter formatter = new CPFFormatter();
		CPFValidator cpfValidator = new CPFValidator();
		String novoCPF;

		// Certifica se CPF não é nulo
		Validate.notNull(cpf);

		// Convere se o CPF é válido
		cpfValidator.assertValid(cpf);

		// Confere se o CPF está formatado
		// e caso positivo, remove a formatação
		if (formatter.isFormatted(cpf)) {
			novoCPF = formatter.unformat(cpf);
		} else {
			novoCPF = cpf;
		}

		return novoCPF;
	}
}
