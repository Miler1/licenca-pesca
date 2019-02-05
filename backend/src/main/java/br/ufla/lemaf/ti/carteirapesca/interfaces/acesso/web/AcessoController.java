package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import br.ufla.lemaf.ti.carteirapesca.application.AcessoApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.SolicitanteRopository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.RemessaBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CarteiraUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Gerador;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ListaLicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Manipula o acesso de usuarios.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/api")
public class AcessoController {

	@Autowired
	private AcessoServiceFacade acessoServiceFacade;
	private SolicitanteRopository solicitanteRopository;

	@Autowired
	private TituloRepository tituloRepository;

	@Autowired
	private RemessaBuilderImpl remessaBuilder;

	@Autowired
	private AcessoApplication acessoApplication;

	/**
	 * Controller para o acesso público. Recebe como parâmetro
	 * obrigatório o CPF do usuário.
	 * <p>
	 * Caso o mesmo tenha cadastro no entrada única, retorna seus dados,
	 * e se o mesmo não tiver cadastro, retornará {@link PessoaDTO} vazio.
	 *
	 * @return {@link PessoaDTO} Pessoa vazia caso não exista a mesma na
	 * base de dados, ou a pessoa instanciada com
	 * seus dados caso exista. Em forma de {@link ResponseEntity}
	 */
	@CrossOrigin("*")
	@PostMapping("/acessar")
	public ResponseEntity<PessoaDTO> acessar(@RequestBody final ValidacaoDTO validacaoDTO) throws Exception {

		WebServiceUtils.validarWebService();

		if(acessoServiceFacade.validaDadosAcessoLicencas(validacaoDTO) == true){

			var pessoa = acessoServiceFacade.acessar(validacaoDTO.getAcessoResource());

			pessoa.add(linkTo(methodOn(AcessoController.class)
				.acessar(validacaoDTO))
				.withSelfRel());

			return new ResponseEntity<>(pessoa, HttpStatus.ACCEPTED);

		}

		throw new Exception("Dados não conferem. Após 3 tentativas erradas, o Cpf/passaporte será bloqueado por 2 horas.");
	}

	@CrossOrigin("*")
	@PostMapping(value="/buscarLicencas")
	public ResponseEntity<ListaLicencaDTO> buscarLicencas(@RequestBody final ValidacaoDTO validacaoDTO) throws Exception {

		WebServiceUtils.validarWebService();

		if(acessoServiceFacade.validaDadosAcessoLicencas(validacaoDTO) == true){

			ListaLicencaDTO listaLicencaDTO = new ListaLicencaDTO();

			PessoaDTO pessoa = acessoServiceFacade.acessar(validacaoDTO.getAcessoResource());

			listaLicencaDTO.setPessoa(pessoa);

			listaLicencaDTO.setLicencas(acessoServiceFacade.buscarLicencasPorPessoaDTO(pessoa));

			return new ResponseEntity<>(listaLicencaDTO, HttpStatus.ACCEPTED);

		} else {
			throw new Exception("Dados não conferem. Após 3 tentativas erradas, o Cpf/passaporte será bloqueado por 2 horas.");
		}
	}

	@CrossOrigin("*")
	@PostMapping("/buscarDados")
	public ResponseEntity buscarDados(@RequestBody final AcessoResource acessoResource) throws Exception {

		WebServiceUtils.validarWebService();

		PessoaDTO pessoa = acessoServiceFacade.acessar(acessoResource);

		if(pessoa == null || pessoa.getNome() == null){

			return new ResponseEntity<>(pessoa, HttpStatus.ACCEPTED);
		}

		if(acessoServiceFacade.solicitanteBloqueado(acessoResource)) {

			throw new Exception("CPF / passaporte bloqueado, tente novamente mais tarde");

		} else {

			ListaLicencaDTO listaLicencaDTO = new ListaLicencaDTO();

			pessoa = acessoServiceFacade.acessar(acessoResource);

			listaLicencaDTO.setPessoa(pessoa);

			listaLicencaDTO.setLicencas(acessoServiceFacade.buscarLicencasPorPessoaDTO(pessoa));

			return new ResponseEntity<>(preencherListaVerificacao(pessoa), HttpStatus.ACCEPTED);
		}

	}


	private static Map<String, Object[]> preencherListaVerificacao(PessoaDTO pessoa) throws IOException {

		Map<String, Object[]> listasVerificacao = new HashMap<>();

		if (!Validate.isNull(pessoa.getCpf())) {

			preencherListaVerificacaoSolicitante(listasVerificacao, pessoa);

		} if(!Validate.isNull(pessoa.getPassaporte())) {

			preencherListaVerificacaoSolicitante(listasVerificacao, pessoa);
		}

		return listasVerificacao;

	}

	private static void preencherListaVerificacaoSolicitante(Map<String, Object[]> listasVerificacao, PessoaDTO pessoa) throws IOException {

		Gerador gerador = new Gerador();
		Random rand = new Random();

		Integer quantidade = 5;
		Integer padrao = rand.nextInt(5) ;
		Integer posicao = padrao > 3 ? Math.abs(padrao/3) : padrao;

		if(posicao > 3) {
			posicao = 0;
		}

		String[] maes = gerador.gerarMaes(quantidade, padrao);

		maes[posicao++] = CarteiraUtils.capitalize(pessoa.getNomeMae());
		listasVerificacao.put("maes", maes);

	}


	@CrossOrigin("*")
	@GetMapping("/remessa")
	public ResponseEntity<InputStreamResource> geraRemessa() throws IOException {

		Remessa remessa = remessaBuilder.geraRemessa();

		if(remessa != null) {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.TEXT_PLAIN);

			InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(remessa.getArquivo().getCaminhoArquivo())));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		}

	}

}
