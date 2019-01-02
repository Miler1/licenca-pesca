package br.ufla.lemaf.ti.carteirapesca.infrastructure.webservices;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTOAssembler;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.EntradaUnicaException;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.Message;
import main.java.br.ufla.lemaf.beans.pessoa.Municipio;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.services.CadastroUnificadoPessoaService;

import java.util.*;

/**
 * Web Service do Entrada Unica.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
public final class CadastroUnificadoService extends CadastroUnificadoPessoaService {

	private static final String LOG_PREFIX = "[Cadastro Unificado] - ";
	private static final int TMR_INIT_CONNECTION = 5000;
	private static final int TMR_PERIOD_CONNECTION = 30000;

	private static CadastroUnificadoService ws = null;

	private static TimerTask taskTryConnection = new TimerTask() {

		@Override
		public void run() {

			try {

				tryConnection();
				cancel();
				log.info(LOG_PREFIX + "Conexão estabelecida com sucesso.");

			} catch (Exception e) {

				resetWS();
				log.error(LOG_PREFIX + "Erro ao tentar estabelecer a conexão.");

			}
		}
	};

	static {

		try {

			tryConnection();

		} catch (Exception ex) {

			log.error(ex.getLocalizedMessage());
			resetWS();

			// Inicia o timer para renovar
			// a conexão com o Entrada Única
			new Timer().schedule(
				taskTryConnection,
				TMR_INIT_CONNECTION,
				TMR_PERIOD_CONNECTION
			);
		}
	}

	/**
	 * Conexão com a aplicação Entrada Única.
	 */
	private static synchronized void tryConnection() {
		ws = new CadastroUnificadoService(
			Properties.clientId(),
			Properties.clientSecret(),
			Properties.portalSegurancaUrl(),
			Properties.cadastroUnificadoUrl()
		);
	}

	/**
	 * Reseta o Web Service.
	 */
	private static void resetWS() {
		ws = null;
	}

	/**
	 * @return O Web Service
	 */
	public static CadastroUnificadoService webService() {
		return ws;
	}

	/**
	 * Construtor.
	 *
	 * @param clientId     O ClientId.
	 * @param clientSecret O clientSecret.
	 * @param urlPortal    A url do Portal de Segurança.
	 * @param urlCadastro  A url do Cadastro Unificado.
	 */
	private CadastroUnificadoService(final String clientId,
	                                 final String clientSecret,
	                                 final String urlPortal,
	                                 final String urlCadastro) {

		super(clientId, clientSecret, urlPortal, urlCadastro);

	}

	/**
	 * Dado o {@param cpf} do Usuário, retorna seus dados.
	 *
	 * @param cpf O CPF do Usuário.
	 * @return O Usuario cadastrado.
	 */
	public Pessoa buscarUsuario(final String cpf) {
		log.debug(LOG_PREFIX + "Buscando Usuário: " + cpf);

		return this.buscarPessoaFisicaPeloCpf(cpf);

	}

	/**
	 * Busca no entrada unica se a pessoa do {@param cpf}
	 * é um usuário do sistema.
	 *
	 * @param cpf O CPF do Usuário.
	 * @return {@code true} se a pessoa for um usuário.
	 */
	public Boolean existeUsuario(final String cpf) {
		log.debug(LOG_PREFIX + "Verificando se o Usuário do CPF: " + cpf + " existe");

		return this.isUser(cpf);

	}

	/**
	 * Cadastra uma pessoa no Entrada Única.
	 *
	 * @param pessoaDTO O DTO de Pessoa
	 * @return Mensagem do serviço de WS do EU
	 */
	public Message cadastrarPessoa(PessoaDTO pessoaDTO) {

		val assembler = new PessoaDTOAssembler();

		var pessoa = assembler.toPessoa(pessoaDTO);

		Message result = this.cadastrarPessoaFisica(pessoa);

		if (result == null) {
			throw new EntradaUnicaException("entradaUnica.erroCadastro");
		}

		return result;
	}

	/**
	 * Busca a lista de municípios cadastrados no Entrada Única.
	 *
	 * @param siglaEstado A sigla do estado
	 * @return Lista de municípios
	 */
	public List<Municipio> getMunicipiosByEstado(String siglaEstado) {

		var paises = this.buscarPaises();
		var brasil = Arrays.stream(paises)
			.filter(pais -> pais.nome.equals("Brasil"))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);

		var estados = this.buscarEstados(brasil.id);
		var estadoSelecionado = Arrays.stream(estados)
			.filter(estado -> estado.sigla.equals(siglaEstado))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);

		var municipios = this.buscarMunicipio(estadoSelecionado.id);

		if (municipios.length == 0)
			throw new EntradaUnicaException("entradaUnica.municipiosNotFound");

		return Arrays.asList(municipios);

	}

	/**
	 * Busca de municipio atraves do id de municipio e estado
	 * @param idMunicipio
	 * @param idEstado
	 * @return
	 */
	public Municipio getMunicipio(Integer idMunicipio, Integer idEstado) {

		var municipios = this.buscarMunicipio(idEstado);

		var municipio = Arrays.stream(municipios)
			.filter(municipioArray -> municipioArray.id.equals(idMunicipio))
			.findFirst();

		return municipio.get();
	}
}
