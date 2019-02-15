package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.web;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Pais;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.CarteiraBuilder;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.PdfGeneratorUtil;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloFormatter;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloValidator;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade.AcessoServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoController;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.CarteiraDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.LicencaPescaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Manipula as consultas da licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Controller
@Transactional
@RequestMapping("/api")
public class ConsultaController {

	private ConsultaServiceFacade facade;

	@Autowired
	private ConsultaApplication consultaApplication;

	@Autowired
	private CarteiraBuilder carteiraBuilder;

	@Autowired
	private ProtocoloBuilder protocoloBuilder;

	@Autowired
	private AcessoServiceFacade acessoServiceFacade;

	@Autowired
	private RegistroApplication registroApplication;

	@Autowired
	PdfGeneratorUtil pdfGenaratorUtil;

	/**
	 * Injetando dependências.
	 *
	 * @param facade A Facede de consulta
	 */
	@Autowired
	public ConsultaController(ConsultaServiceFacade facade) {
		this.facade = facade;
	}

	/**
	 * Controller para a consulta de licença. Recebe como parâmetro
	 * o protocolo.
	 *
	 * @param protocolo O protocolo buscado
	 * @return {@link LicencaDTO} A Licença. Em forma de {@link ResponseEntity}
	 */
	@CrossOrigin("*")
	@GetMapping("/consultar")
	public ResponseEntity<LicencaDTO> consultar(@RequestParam final String protocolo) {

		var licenca = facade.consultarLicencaDTO(protocolo);

		if (licenca == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		licenca.add(linkTo(methodOn(ConsultaController.class)
			.consultar(protocolo))
			.withSelfRel());

		return new ResponseEntity<>(licenca, HttpStatus.OK);

	}

	@CrossOrigin("*")
	@GetMapping("/consultarCarteira")
	public ResponseEntity<CarteiraDTO> consultarCarteira(@RequestParam final String protocolo) throws Exception {

		CarteiraDTO carteiraDTO = new CarteiraDTO();

		var licenca = facade.consultar(protocolo);


		carteiraDTO.setLicenca(licenca);


		var  cpf = "";
		var passaporte = "";
		if(licenca.solicitante().getIdentity().cpf() != null){
			cpf = licenca.solicitante().getIdentity().cpf().getNumero();
		} else {
			cpf = null;
		}
		if(licenca.solicitante().getIdentity().passaporte() != null){
			passaporte = licenca.solicitante().getIdentity().passaporte().getNumero();
		} else {
			passaporte = null;
		}

		AcessoResource acessoResource = new AcessoResource(cpf, passaporte);

		var pessoa = acessoServiceFacade.acessar(acessoResource);

		ValidacaoDTO validacaoDTO = new ValidacaoDTO(acessoResource);

		pessoa.add(linkTo(methodOn(AcessoController.class)
			.acessar(validacaoDTO))
			.withSelfRel());

		carteiraDTO.setPessoaDTO(pessoa);

		if(licenca == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(carteiraDTO, HttpStatus.OK);
	}

	/**
	 * Download do boleto.
	 *
	 * @param protocolo O protocolo do Boleto
	 * @return O arquivo
	 */
	@CrossOrigin("*")
	@GetMapping("/boleto")
	public ResponseEntity<InputStreamResource> downloadBoleto(@RequestParam String protocolo) {

		try {

			var licenca = facade.consultar(protocolo);

			String caminhoBoleto;

			if(licenca.getTitulo().getDataVencimento().compareTo(LocalDate.now()) != -1) {
				caminhoBoleto = licenca.getTitulo().getArquivoBoleto().getCaminhoArquivo();
			} else {
				caminhoBoleto = registroApplication.regerarBoleto(licenca);
			}

			var boleto = new File(caminhoBoleto);

			var httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_PDF);

			var isr = new InputStreamResource(new FileInputStream(boleto));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} catch (IOException | NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Download da carteira de pesca.
	 *
	 * @param protocolo O protocolo da Carteira
	 * @return O arquivo
	 */
	@CrossOrigin("*")
	@GetMapping("/carteira")
	public ResponseEntity<InputStreamResource> downloadCarteira(@RequestParam String protocolo) {

		try {

			Protocolo protocoloObj;
			if (protocolo.length() > 9) {

				var formatterNovo = new ProtocoloFormatter("$1-$2/$3-$4", ProtocoloValidator.FORMATED_RENOVADO, "$1$2$3$4", ProtocoloValidator.UNFORMATED_RENOVADO);
				protocoloObj = new Protocolo(protocolo, formatterNovo);
			} else {
				protocoloObj = new Protocolo(protocolo);
			}

			var licenca = consultaApplication.consulta(protocoloObj);
			var solicitante = licenca.solicitante();
			var pessoa = registroApplication.buscarDadosSolicitante(solicitante);
			var carteira = facade.gerarCarteira(protocoloObj, licenca, pessoa);

			var httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_PDF);

			var isr = new InputStreamResource(new FileInputStream(carteira));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>((InputStreamResource) null, HttpStatus.NOT_FOUND);

		}

	}

	@CrossOrigin("*")
	@GetMapping("/teste")
	public ResponseEntity<InputStreamResource> downloadCarteira() {

		try {


			Map<String,String> data = new HashMap<String,String>();
			data.put("baseUrl", Properties.baseUrl());

			var httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_PDF);

			var isr = new InputStreamResource(new FileInputStream(pdfGenaratorUtil.createPdf("cobranca",data)));

			return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>((InputStreamResource) null, HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * Autenticidade da carteira, trazer todos os dados da carteira, menos limite de captura
	 * code do QR que é o numero da licença
	 * pessoa: Nome, CPF/CNPJ, enderecos{CEP, PAIS, MUNICIPIO}
	 * licenca: protocolo(numero da licenca), modalidade, emissao(dataCriacao), validade
	 * */

	@CrossOrigin("*")
	@GetMapping("/informacao-carteira")
	public ResponseEntity<LicencaPescaDTO> buscarDadosCarteira(
		@RequestParam final String protocolo){

		var protocoloObj = new Protocolo(protocolo);
		var licenca = consultaApplication.consulta(protocoloObj);
		var solicitante = licenca.solicitante();
		var pessoa = registroApplication.buscarDadosSolicitante(solicitante);

		var licencaPesca = new LicencaPescaDTO(licenca, protocoloObj, pessoa);



		return new ResponseEntity<>(licencaPesca, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@GetMapping("/fetch-paises")
	public ResponseEntity<List<Pais>> fetchPaises(){

		var listPaises = consultaApplication.fetchPaises();

		return new ResponseEntity<>(listPaises, HttpStatus.OK);
	}

}
