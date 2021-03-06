package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.CPF;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Passaporte;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.SolicitanteRopository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTOAssembler;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ValidationException;
import lombok.val;
import lombok.var;
import br.ufla.lemaf.beans.pessoa.FiltroPessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Facade do serviço de Acesso implementado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */


@Service
public class AcessoServiceFacadeImpl implements AcessoServiceFacade {


	private static final Logger log = LoggerFactory.getLogger(AcessoServiceFacadeImpl.class);

	@Autowired
	private AcessoApplication acessoApplication;

	@Autowired
	private SolicitanteRopository solicitanteRopository;

	/**
	 * Injetando Dependência.
	 *
	 * @param acessoApplication Serviço de Acesso da camada de application.
	 */
	@Autowired
	public AcessoServiceFacadeImpl(AcessoApplication acessoApplication,
									final SolicitanteRopository solicitanteRopository) {
		this.acessoApplication = acessoApplication;
		this.solicitanteRopository = solicitanteRopository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaDTO acessar(final AcessoResource resource) {

		val pessoaDTOAssembler = new PessoaDTOAssembler();
		AcessoResource acessoResource;

		// CPF e Passaporte não podem ser ambos nulos.
		if (resource.getCpf() == null && resource.getPassaporte().isEmpty())
			throw new ValidationException("acesso.resourceInvalid.cpfAndPassaporte");

		/* Valida e desformata CPF se não
		 * existir Passaporte.
		 * Caso contrario, reinstancia o
		 * recursoValidado com os valores de
		 * resource.
		 */
		if (resource.getCpf() != null && !resource.getCpf().isEmpty()) {
			acessoResource = new AcessoResource(
				CPFUtils.unformat(resource.getCpf()),
				resource.getPassaporte()
			);
		} else {
			acessoResource = new AcessoResource(
				resource.getCpf(),
				resource.getPassaporte()
			);
		}
		// Converte dado Pessoa em DTO de Pessoa
		PessoaDTO pessoaDTO = pessoaDTOAssembler.toDTO(
			acessoApplication.identificar(acessoResource)
		);

		Solicitante solicitante;
		if(pessoaDTO.getCpf() != null){
			solicitante = solicitanteRopository.findByIdentityCpfNumero(pessoaDTO.getCpf());
		} else {
			solicitante = solicitanteRopository.findByIdentityPassaporteNumero(pessoaDTO.getPassaporte());
		}
		if(solicitante != null){

			pessoaDTO.setEnderecoEstrangeiro(solicitante.getEnderecoEstrangeiro());
		}
		return pessoaDTO;

	}

	@Override
	public List<Licenca> buscarLicencasPorPessoaDTO(PessoaDTO pessoaDTO) {

		Solicitante solicitante = buscarSolicitante(pessoaDTO);

		if(solicitante == null && pessoaDTO.getNome() == null) {

			throw new ValidationException("acesso.resourceInvalid.pessoaNaoCadastrada");

		} else if(solicitante == null){
			return null;
		}

		return solicitante.buscarTodasLicencas();
	}

	private Solicitante buscarSolicitante(PessoaDTO pessoaDTO) {

		Solicitante solicitante = null;

		if (pessoaDTO.getCpf() != null) {

			solicitante = solicitanteRopository.findByIdentityCpfNumero(pessoaDTO.getCpf());

		} else if(pessoaDTO.getPassaporte() != null){

			solicitante = solicitanteRopository.findByIdentityPassaporteNumero(pessoaDTO.getPassaporte());
		}

		return solicitante;

	}

	@Override
	public Boolean validaDadosAcessoLicencas(ValidacaoDTO validacaoDTO) throws Exception {

		PessoaDTO pessoaDTO = new PessoaDTO(validacaoDTO.getAcessoResource().getCpf(), validacaoDTO.getAcessoResource().getPassaporte());

		if(validacaoDTO.getNomeMae() == null || validacaoDTO.getDataNascimento() == null){

			throw new Exception("Os dados não foram informados ou estão inválidos!");
		}

		Solicitante solicitante = buscarSolicitante(pessoaDTO);

		if(solicitanteBloqueado(validacaoDTO.getAcessoResource())){

			throw new Exception("CPF / passaporte bloqueado, tente novamente mais tarde");
		}

		if(solicitante != null && solicitante.getNumeroTentativas() != null && solicitante.getNumeroTentativas() == 3) {

			throw new Exception("Cpf / passaporte bloqueado, tente novamente após 2 horas");

		} else if(solicitante == null) {

			CPF cpf = null;
			Passaporte passaporte = null;

			if(validacaoDTO.getAcessoResource().getPassaporte() == null) {
				cpf = new CPF(validacaoDTO.getAcessoResource().getCpf());
			} else {
				passaporte = new Passaporte(validacaoDTO.getAcessoResource().getPassaporte());
			}

			solicitante = new Solicitante(cpf, passaporte);
		}

		Boolean dadosValidos = dadosAcessoValidos(validacaoDTO);

		if(!dadosValidos) {


			solicitante.atualizaNumeroTentativas();

			solicitanteRopository.save(solicitante);


			return dadosValidos;

		} else {

			if(solicitante != null){

				solicitante.limpaDadosDesbloqueioSolicitante();
			}

			return dadosValidos;
		}

	}

	public Boolean solicitanteBloqueado(AcessoResource acessoResource) throws Exception {

		PessoaDTO pessoaDTO = new PessoaDTO(acessoResource.getCpf(), acessoResource.getPassaporte());

		Solicitante solicitante = buscarSolicitante(pessoaDTO);

		if(solicitante != null && solicitante.getDataDesbloqueio() != null) {

			if (DateUtils.dataMaiorQue(new Date(), solicitante.getDataDesbloqueio())) {

				solicitante.limpaDadosDesbloqueioSolicitante();
				solicitanteRopository.save(solicitante);

				return false;
			}

			return true;

		}else if(solicitante != null
				&& solicitante.getDataUltimaTentativa() != null
				&& solicitante.getNumeroTentativas() < Constants.NUMERO_TENTATIVAS_BLOQUEIO_SOLICITANTE){

			Date dataUltimaTentaiva = DateUtils.somarHorasData(solicitante.getDataUltimaTentativa(), 12);

			if(DateUtils.dataMaiorQue(new Date(), dataUltimaTentaiva)){
				solicitante.limpaDadosDesbloqueioSolicitante();
				return false;
			}

		}

		return false;
	}


	private Boolean dadosAcessoValidos(ValidacaoDTO validacaoDTO) {

		FiltroPessoa filtroPessoa = new FiltroPessoa();
		filtroPessoa.isUsuario = false;

		if(validacaoDTO.getAcessoResource().getCpf() != null) {
			filtroPessoa.login = validacaoDTO.getAcessoResource().getCpf();
		} else {
			filtroPessoa.passaporte = validacaoDTO.getAcessoResource().getPassaporte();
		}

		var pessoa =  WebServiceUtils
			.webServiceEU()
			.buscarPessoaComFiltro(filtroPessoa);

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(pessoa.dataNascimento);
		Calendar dateCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		pessoa.dataNascimento = dateCalendar.getTime();

		try{
			calendar.setTime( new SimpleDateFormat("dd/MM/yyyy").parse(validacaoDTO.getDataNascimentoString()));
		} catch (Exception e) {}

//		calendar.setTime(validacaoDTO.getDataNascimento());
		Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		validacaoDTO.setDataNascimento(calendar1.getTime());

		if(pessoa.dataNascimento.compareTo(validacaoDTO.getDataNascimento()) != 0 || !pessoa.nomeMae.toUpperCase().trim().equals(validacaoDTO.getNomeMae().toUpperCase().trim()) ){

			return false;
		}

		return true;

	}
}
