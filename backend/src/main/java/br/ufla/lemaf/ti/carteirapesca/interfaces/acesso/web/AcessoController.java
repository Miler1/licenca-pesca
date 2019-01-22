package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web;

import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.SolicitanteRopository;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CarteiraUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Gerador;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ListaLicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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

	private AcessoServiceFacade acessoServiceFacade;
	private SolicitanteRopository solicitanteRopository;


	/**
	 * Injeta a dependencia da controller.
	 *
	 * @param acessoServiceFacade Serviço de Facade da camada
	 *                            de interface.
	 */
	@Autowired
	public AcessoController(final AcessoServiceFacade acessoServiceFacade) {
		this.acessoServiceFacade = acessoServiceFacade;
	}


	/**
	 * Controller para o acesso público. Recebe como parâmetro
	 * obrigatório o CPF do usuário.
	 * <p>
	 * Caso o mesmo tenha cadastro no entrada única, retorna seus dados,
	 * e se o mesmo não tiver cadastro, retornará {@link PessoaDTO} vazio.
	 *
	 * @param acessoResource Paramêtro com o recurso de
	 *                       acesso do Usuário.
	 * @return {@link PessoaDTO} Pessoa vazia caso não exista a mesma na
	 * base de dados, ou a pessoa instanciada com
	 * seus dados caso exista. Em forma de {@link ResponseEntity}
	 */
	@CrossOrigin("*")
	@PostMapping("/acessar")
	public ResponseEntity<PessoaDTO> acessar(@RequestBody final AcessoResource acessoResource) {

		var pessoa = acessoServiceFacade.acessar(acessoResource);
		pessoa.add(linkTo(methodOn(AcessoController.class)
			.acessar(acessoResource))
			.withSelfRel());

		return new ResponseEntity<>(pessoa, HttpStatus.ACCEPTED);

	}

	@CrossOrigin("*")
	@PostMapping(value="/buscarLicencas")
	public ResponseEntity<ListaLicencaDTO> buscarLicensas(@RequestBody final AcessoResource acessoResource) throws Exception {

		if(acessoServiceFacade.validaDadosAcessoLicencas(acessoResource) == true){
			var listaLicencaDTO = new ListaLicencaDTO();

			var pessoa = acessoServiceFacade.acessar(acessoResource);

			listaLicencaDTO.setPessoa(pessoa);

			listaLicencaDTO.setLicencas(acessoServiceFacade.buscarLicencasPorPessoaDTO(pessoa));

			return new ResponseEntity<>(listaLicencaDTO, HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

//	@CrossOrigin("*")
//	@PostMapping("/verificaDados")
	public ResponseEntity verificaDados(@RequestBody final ValidacaoDTO validacaoDTO) throws Exception {

//		if(acessoServiceFacade.validaDadosAcessoLicencas(validacaoDTO)) {
//
//			return new ResponseEntity<>(HttpStatus.OK);
//
//		}


		return new ResponseEntity<>(HttpStatus.OK);

	}


	@CrossOrigin("*")
	@PostMapping("/buscarDados")
	public ResponseEntity buscarDados(@RequestBody final AcessoResource acessoResource) throws Exception {


		var listaLicencaDTO = new ListaLicencaDTO();

		var pessoa = acessoServiceFacade.acessar(acessoResource);

		listaLicencaDTO.setPessoa(pessoa);

		listaLicencaDTO.setLicencas(acessoServiceFacade.buscarLicencasPorPessoaDTO(pessoa));

		return new ResponseEntity<>(preencherListaVerificacao(pessoa), HttpStatus.ACCEPTED);

	}


	private static Map<String, Object[]> preencherListaVerificacao(PessoaDTO pessoa) {


		Map<String, Object[]> listasVerificacao = new HashMap<>();


		if (!Validate.isNull(pessoa.getCpf())) {

			preencherListaVerificacaoSolicitante(listasVerificacao, pessoa);

		} if(!Validate.isNull(pessoa.getPassaporte())) {

			preencherListaVerificacaoSolicitante(listasVerificacao, pessoa);
		}

		return listasVerificacao;

	}

	private static void preencherListaVerificacaoSolicitante(Map<String, Object[]> listasVerificacao, PessoaDTO pessoa) {

		Gerador gerador = new Gerador();

		Integer quantidade = 5;
		Integer padrao = Integer.valueOf(pessoa.getCpf().substring(pessoa.getCpf().length()-1));
		Integer posicao = padrao > 3 ? Math.abs(padrao/3) : padrao;

//		String[] municipios = gerador.gerarMunicipios(quantidade, padrao);
//		municipios[posicao] = CarteiraUtils.capitalize(pessoa.getEnderecoPrincipal().getMunicipio().nome);
//		listasVerificacao.put("municipios", municipios);

		if(posicao > 3) {
			posicao = 0;
		}

		String[] maes = gerador.gerarMaes(quantidade, padrao);

		maes[posicao++] = CarteiraUtils.capitalize(pessoa.getNomeMae());
		listasVerificacao.put("maes", maes);

	}

}
