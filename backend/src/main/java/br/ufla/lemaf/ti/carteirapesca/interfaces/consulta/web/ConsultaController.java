package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.web;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.CarteiraBuilder;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.ConsultaServiceFacade;
import br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto.LicencaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.LicencaPescaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ProtocoloDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroController;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
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

//
//	@Autowired
//	private DadosCarteiraBuilder dadosCarteiraBuilder;

	@Autowired
	private ProtocoloBuilder protocoloBuilder;


	@Autowired
	private RegistroApplication registroApplication;

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

		var licenca = facade.consultar(protocolo);

		if (licenca == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		licenca.add(linkTo(methodOn(ConsultaController.class)
			.consultar(protocolo))
			.withSelfRel());

		return new ResponseEntity<>(licenca, HttpStatus.OK);

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

			var rotaDoBoleto = facade.buscarCaminho(protocolo, Constants.BOLETO);
			var boleto = new File(rotaDoBoleto);

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

			var protocoloObj = new Protocolo(protocolo);
			var licenca = consultaApplication.consulta(protocoloObj);
			var solicitante = licenca.getSolicitante();
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
		var solicitante = licenca.getSolicitante();
		var pessoa = registroApplication.buscarDadosSolicitante(solicitante);

		var licencaPesca = new LicencaPescaDTO(licenca, protocoloObj, pessoa);

		return new ResponseEntity<>(licencaPesca, HttpStatus.OK);
	}

}
