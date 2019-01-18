package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Message;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoController;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.RegistroServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.*;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.val;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Facade do serviço de Registro implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Service
public class RegistroServiceFacadeImpl implements RegistroServiceFacade {

	private static final String REGISTRO_ERROR_PREFIX = "registro.resourceInvalid.";
	private static final String REQUIRED_MESSAGE = REGISTRO_ERROR_PREFIX + "required";

	private RegistroApplication registroApplication;

	@Autowired
	private LicencaRepository licencaRepository;

	/**
	 * Injetando dependências.
	 *
	 * @param registroApplication O Serviço de registro da camada de aplicação
	 */
	@Autowired
	public RegistroServiceFacadeImpl(RegistroApplication registroApplication) {
		this.registroApplication = registroApplication;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProtocoloDTO registrar(final RegistroResource resource) {

		val assembler = new ProtocoloDTOAssembler();

		validateResource(resource);

		val pessoaValidada = new PessoaDTO(resource.getPessoa());

		return assembler.toDTO(registroApplication.registrar(
			new RegistroResource(
				pessoaValidada,
				resource.getInformacaoComplementar()
			)));

	}

	@Override
	public void atualizarCondicaoVencimento() {
		licencaRepository.alterarVencimento(new Date());
		return ;
	}

	/**
	 * Valida os dados do recurso de registro.
	 *
	 * @param resource O recurso
	 */
	private static void validateResource(final RegistroResource resource) {
		var descricaoErro = new ArrayList<String>();

		descricaoErro.addAll(validatePessoa(resource.getPessoa()));
		descricaoErro.addAll(validateInformacaoComplementar(resource.getInformacaoComplementar()));

		if (!descricaoErro.isEmpty())
			throw new ValidationException(
				"erro.listaError",
				StringUtils.join(descricaoErro, "\n")
			);

	}

	/**
	 * Valida se os dados de pessoa estão de acordo com o esperado.
	 * <p>
	 * Regra:
	 * <a
	 * src="http://gitlab.ti.lemaf.ufla.br/ipaam/carteira-de-pesca/wikis/
	 * licenca-pesca#etapa-1-identifica%C3%A7%C3%A3o"
	 * >
	 * Etapa 1: Identificação</a>
	 *
	 * @param pessoa A pessoa do recurso
	 * @return Lista com mensagens de erros
	 */
	private static List<String> validatePessoa(final PessoaDTO pessoa) {
		var camposInvalidos = new ArrayList<String>();

		if (Validate.isNull(pessoa.getCpf()) && Validate.isNull(pessoa.getPassaporte()))
			camposInvalidos.add(Message.get(REGISTRO_ERROR_PREFIX + "cpfAndPassaporte"));

		if (!Validate.isNull(pessoa.getCpf()) && !Validate.isCpfValid(pessoa.getCpf()) && Validate.isNull(pessoa.getPassaporte()))
			camposInvalidos.add(Message.get(REGISTRO_ERROR_PREFIX + "cpfInvalido"));

		if (Validate.isNull(pessoa.getNome()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "nome"));

		if (Validate.isNull(pessoa.getDataNascimento()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "data de nascimento"));
		else if (!Validate.isInPast(pessoa.getDataNascimento()))
			camposInvalidos.add(Message.get(REGISTRO_ERROR_PREFIX + "dataNascimentoInFuture"));

		if (Validate.isNull(pessoa.getSexo()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "sexo"));

		if (Validate.isNull(pessoa.getNomeMae()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "nome da mãe"));

		if (Validate.isNull(pessoa.getEmail())) {
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "email"));
		} else if (!Validate.isEmail(pessoa.getEmail())) {
			camposInvalidos.add(Message.get(REGISTRO_ERROR_PREFIX + "emailInvalid", pessoa.getEmail()));
		}

		if (Validate.isNull(pessoa.getEnderecoPrincipal())){

			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "endereço"));
		}
		else if(Validate.isNull(pessoa.getEnderecoPrincipal())){

			camposInvalidos.addAll(validateEndereco(pessoa.getEnderecoPrincipal()));
		}

		if (!Validate.isNull(pessoa.getEnderecoPrincipal())
			&& pessoa.getEnderecoPrincipal().getZonaLocalizacao().equals(Constants.ZONA_RURAL)) {
			if (!Validate.isNull(pessoa.getEnderecoCorrespondencia())) {
				camposInvalidos.addAll(validateEndereco(pessoa.getEnderecoCorrespondencia()));
			} else {
				camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "endereço de correspondência"));
			}
		}

		return camposInvalidos;

	}

	/**
	 * Valida os dados de endereço.
	 *
	 * @param endereco Objeto de endereço
	 * @return Lista com mensagens de erros
	 */
	private static List<String> validateEndereco(final EnderecoDTO endereco) {
		var camposInvalidos = new ArrayList<String>();

		if (Validate.isNull(endereco.getZonaLocalizacao()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "zona de localização"));

		if (Validate.isNull(endereco.getLogradouro()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "logradouro"));

		if (Validate.isNull(endereco.getNumero()) && Validate.isNull(endereco.getSemNumero())
			|| endereco.getSemNumero() ^ Validate.isNull(endereco.getNumero()))
			camposInvalidos.add(Message.get(REGISTRO_ERROR_PREFIX + "endereco.numeroAndSemNumero"));

		if (Validate.isNull(endereco.getBairro()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "bairro"));

		if (Validate.isNull(endereco.getCep()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "CEP"));

		if (Validate.isNull(endereco.getUf()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "UF"));

		if (Validate.isNull(endereco.getMunicipio()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "município"));

		if (endereco.getZonaLocalizacao().equals(Constants.ZONA_RURAL)
			&& Validate.isNull(endereco.getDescricaoAcesso()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "descrição de acesso"));

		return camposInvalidos;
	}

	/**
	 * Valida se os dados de informações complementares estão como esperados.
	 * <p>
	 * Regra:
	 * <a
	 * src="http://gitlab.ti.lemaf.ufla.br/ipaam/carteira-de-pesca/wikis/
	 * licenca-pesca#etapa-2-informa%C3%A7%C3%B5es-complementares">
	 * Etapa 2: Informações Complementares</a>
	 *
	 * @param info Os dados de Informação complementar
	 * @return Lista com mensagens de erros
	 */
	private static List<String> validateInformacaoComplementar(final InformacaoComplementarDTO info) {
		var camposInvalidos = new ArrayList<String>();

		// Confere dados obrigatórios
		if (Validate.isNull(info.getModalidadePesca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "modalidade de pesca"));

		if (Validate.isNull(info.getLocalPesca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "local de pesca"));

		if (Validate.isNull(info.getRendaMensal()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "renda mensal"));

		if (Validate.isNull(info.getDiasPescaPorAno()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "média de pesca"));

		if (Validate.isNull(info.getGastoMedioPesca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "gasto médio por pesca"));

		if (Validate.isNull(info.getFaixaEtaria()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "faixa etária"));

		if (Validate.isNull(info.getLocalizacaoPreferencialPesca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "localização preferencial de pesca"));

		if (Validate.isNull(info.getMaterialPesca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "material de pesca"));

		if (Validate.isNull(info.getTipoIsca()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "tipo de isca"));

		if (Validate.isNull(info.getModalidadeMaisPraticada()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "modalidade mais praticada"));

		if (Validate.isNull(info.getAgenciaTurismo()))
			camposInvalidos.add(Message.get(REQUIRED_MESSAGE, "agência de turismo"));

		return camposInvalidos;

	}

}
