package br.ufla.lemaf.ti.carteirapesca.infrastructure;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.ApplicationGlobalProperties;
import lombok.extern.slf4j.Slf4j;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.services.CadastroUnificadoPessoaService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Web Service do Entrada Unica.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
public class CadastroUnificadoService extends CadastroUnificadoPessoaService {

	private static final int TMR_INIT_CONNECTION = 5000; //5s
	private static final int TMR_PERIOD_CONNECTION = 30000; //30s

	private static final String LOG_PREFIX = "[CADASTRO-UNIFICADO-WS]";

	private static TimerTask taskTryConnection = new TimerTask() {
		@Override
		public void run() {
			try {

				tryConnection();
				cancel();
				log.info(
					LOG_PREFIX
						+ " - conexão estabelecida"
						+ "com sucesso."
				);
			} catch (Exception e) {
				ws = null;
				log.error(
					LOG_PREFIX
						+ " - erro ao tentar"
						+ "estabelecer a conexão."
				);
			}
		}
	};

	public static CadastroUnificadoService ws = null;

	static {

		try {

			tryConnection();
		} catch (Exception ex) {

			ex.printStackTrace();
			ws = null;

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
			ApplicationGlobalProperties.ENTRADA_UNICA_CLIENTE_ID,
			ApplicationGlobalProperties.ENTRADA_UNICA_CLIENTE_SECRET,
			ApplicationGlobalProperties.ENTRADA_UNICA_URL_PORTAL_SEGURANCA,
			ApplicationGlobalProperties.ENTRADA_UNICA_URL_CADASTRO_UNIFICADO
		);
	}

	/**
	 * Construtor.
	 *
	 * @param clientId     O ClientId.
	 * @param clientSecret O clientSecret.
	 * @param urlPortal    A url do Portal de Segurança.
	 * @param urlCadastro  A url do Cadastro Unificado.
	 */
	public CadastroUnificadoService(final String clientId,
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

		return this.isUser(cpf);

	}


}
