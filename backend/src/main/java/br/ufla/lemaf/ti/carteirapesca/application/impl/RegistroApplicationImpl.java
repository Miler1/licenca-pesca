package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.application.TaxaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.InformacaoComplementar;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.*;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.ModalidadeRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloFormatter;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloValidator;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.InformacaoComplementarService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaEUDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.FiltroPessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do serviço de registro da camada de aplicação.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class RegistroApplicationImpl implements RegistroApplication {

	@Autowired
	private ModalidadeRepository modalidadeRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private ProtocoloBuilder protocoloBuilder;

	@Autowired
	private LicencaRepository licencaRepository;

	@Autowired
	private InformacaoComplementarService informacaoComplementarService;

	@Autowired
	private SolicitanteRopository solicitanteRopository;

	@Autowired
	TaxaApplication taxaApplication;

	@Override
	public Protocolo registrar(final RegistroResource resource) {

		WebServiceUtils.validarWebService();

		var solicitante = getSolicitante(resource);

		Modalidade modalidade = gerarModalidade(resource.getInformacaoComplementar().getModalidadePesca());
		Licenca licenca;

		if(!solicitante.pussuiLicencaAtiva(modalidade)) {

			licenca = criarLicenca(resource, null);
			licenca.protocolo();

//			protocolo = solicitante.adicionarLicenca(licenca, false);

		} else if(!solicitante.pussuiLicencaAtiva(modalidade)) {
			throw new SolicitanteException("solicitante.licenca.ativa");
		} else {
			throw new SolicitanteException("solicitante.licenca.mesma.modalidade");
		}

		if(resource.getPessoa().getEnderecoEstrangeiro() != null && !resource.getPessoa().getEnderecoEstrangeiro().isEmpty()){
			solicitante.setEnderecoEstrangeiro(resource.getPessoa().getEnderecoEstrangeiro());
		} else {
			solicitante.setEnderecoEstrangeiro(null);
		}

		licenca.setSolicitante(solicitante);

		licencaRepository.save(licenca);

		taxaApplication.geraDocumentoArrecadacao(licenca);

		return licenca.getProtocolo();
	}

	@Override
	public Protocolo renovarLicenca(RegistroResource resource, String codigoProtocolo) {

		var licenca = licencaRepository.buscaPeloProtocolo(codigoProtocolo);

		if (licenca.getPodeRenovar()) {

			var solicitante = getSolicitante(resource);

			String protocoloNovo = this.calcularNovoProtocolo(codigoProtocolo);

			var novaLicenca = criarLicenca(resource, protocoloNovo);

			Protocolo protocolo = solicitante.adicionarLicenca(novaLicenca, true);

			for(Licenca licenca1: solicitante.getLicenca()) {

				String protocoloSemFormatacao = licenca1.getProtocolo().getCodigoFormatado().replace("-", "").replace("/", "");

				if(protocoloSemFormatacao.equals(codigoProtocolo)) {
					licenca1.setStatus(statusRepository.findById(Status.StatusEnum.RENOVADO.id).get());
				}

			}

			solicitanteRopository.save(solicitante);

			licenca.setSolicitante(solicitante);

			taxaApplication.geraDocumentoArrecadacao(licenca);

			return protocolo;

		} else {

			throw new SolicitanteException("solicitante.licenca.ativa");
		}

	}



	private String calcularNovoProtocolo(String protocolo){

		var formatterNovo = new ProtocoloFormatter("$1-$2/$3-$4", ProtocoloValidator.FORMATED_RENOVADO, "$1$2$3$4", ProtocoloValidator.UNFORMATED_RENOVADO);

		val formatterAntigo = new ProtocoloFormatter();

		if(protocolo.length() == 9){
			return formatterAntigo.format(protocolo) + "-01";
		}
		protocolo = formatterNovo.format(protocolo);

		String[] partes = protocolo.split("-");
		String ultimaParte = partes[partes.length - 1];

		Integer valor = Integer.valueOf(ultimaParte) + 1;

		ultimaParte = valor.toString();

		if(ultimaParte.length() == 1) {
			ultimaParte = "0" + ultimaParte;
		}

		protocolo = partes[0] + "-" + partes[1] + "-" + ultimaParte;

		return protocolo;
	}

	/**
	 * Cria uma licença de pesca.
	 *
	 * @param resource Os dados de registro.
	 * @return A Licenca
	 */
	private Licenca criarLicenca(final RegistroResource resource, String codigoProtocolo) {

		Modalidade modalidade = gerarModalidade(resource.getInformacaoComplementar().getModalidadePesca());

		Protocolo protocolo;

		if(codigoProtocolo == null) {
			protocolo = new Protocolo(protocoloBuilder.gerarProtocolo(modalidade));
		} else {
			protocolo = new Protocolo(codigoProtocolo);
		}

		Status status = statusRepository.findById(Status.StatusEnum.ATIVO_AGUARDANDO_PAGAMENTO.id).get();

		InformacaoComplementar informacaoComplementar = informacaoComplementarService.toInformacaoComplementar(resource.getInformacaoComplementar());

		return new Licenca(protocolo, modalidade, informacaoComplementar, status);

	}

	/**
	 * Cadastra o usuário no Entrada Única.
	 *
	 * @param pessoa A pessoa
	 */
	private void cadastrarPessoa(PessoaEUDTO pessoa) {

		WebServiceUtils.validarWebService();

		FiltroPessoa filtroPessoa = new FiltroPessoa();
		filtroPessoa.login = pessoa.getCpf();
		filtroPessoa.passaporte = pessoa.getPassaporte();
		filtroPessoa.isUsuario = false;

		var pessoaEU = WebServiceUtils
			.webServiceEU()
			.buscarPessoaComFiltro(filtroPessoa);

		if (pessoaEU == null || pessoaEU.nome == null) {

			WebServiceUtils
				.webServiceEU()
				.cadastrarPessoa(pessoa);
		}
	}

	/**
	 * Constroi uma modalidade a partir da modalidade.
	 *
	 * @param tipo O tipo da modalidade
	 * @return A Modalidade
	 */
	public Modalidade gerarModalidade(Integer tipo) {

		return modalidadeRepository.findById(tipo).get();
	}

	/**
	 * Busca o solicitante se o mesmo já estiver registrado ou
	 * cria um novo caso não esteja registrado.
	 *
	 * @param resource Os dados de registro
	 * @return O Solicitante
	 */
	private Solicitante getSolicitante(final RegistroResource resource) {

		var cpf = !Validate.isNull(resource.getPessoa().getCpf())
			? new CPF(resource.getPessoa().getCpf())
			: null;
		var passaporte = !Validate.isNull(resource.getPessoa().getPassaporte())
			? new Passaporte(resource.getPessoa().getPassaporte())
			: null;

		Solicitante solicitante = null;

		if (cpf != null) {
			solicitante = solicitanteRopository.findByIdentityCpfNumero(cpf.getNumero());
		} else if (passaporte != null) {
			solicitante = solicitanteRopository.findByIdentityPassaporteNumero(passaporte.getNumero());
		}

		if (solicitante == null) {
			solicitante = new Solicitante(cpf, passaporte);
			cadastrarPessoa(resource.getPessoa().toPessoaEUDTO());
		}

		return solicitante;
	}

	/**
	 * Busca os dados do solicitante no Entrada Unica.
	 *
	 * @param solicitante O Solicitante
	 * @return A Pessoa: Dados de solicitante no EU
	 */
	public Pessoa buscarDadosSolicitante(Solicitante solicitante) {
		var identificador = solicitante.identity();

		FiltroPessoa filtroPessoa = new FiltroPessoa();
		filtroPessoa.isUsuario = false;

		if(identificador.isCPF()) {
			filtroPessoa.login = identificador.cpf().getNumero();
		} else {
			filtroPessoa.passaporte = identificador.passaporte().getNumero();
		}

		return WebServiceUtils
			.webServiceEU()
			.buscarPessoaComFiltro(filtroPessoa);
	}

	@Override
	public String regerarBoleto(Licenca licenca) {

//		taxaApplication.geraDocumentoArrecadacao(licenca);

//		Pessoa pessoa = buscarDadosSolicitante(licenca.solicitante());
//
//		Titulo titulo = tituloBuilder.gerarDocumentoPagamento(licenca.getProtocolo(), licenca.modalidade(), pessoa);
//
//		licenca.setTitulo(titulo);
//		licencaRepository.save(licenca);

		return null; //titulo.getArquivoBoleto().getCaminhoArquivo();
	}

}
