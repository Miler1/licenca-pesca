package br.ufla.lemaf.ti.carteirapesca.infrastructure;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.extern.slf4j.Slf4j;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.services.CadastroUnificadoPessoaService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Web Service do Entrada Unica.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@SuppressWarnings("CheckStyle")
@EnableAutoConfiguration
public class CadastroUnificadoService extends CadastroUnificadoPessoaService {

	private static final String LOG_PREFIX = "[Cadastro Unificado] - ";
	private static final int TMR_INIT_CONNECTION = 5000;
	private static final int TMR_PERIOD_CONNECTION = 30000;


	private static TimerTask taskTryConnection = new TimerTask() {
		@Override
		public void run() {
			try {

				tryConnection();
				cancel();
				log.info(
					LOG_PREFIX + "Conexão estabelecida com sucesso."
				);
			} catch (Exception e) {
				ws = null;
				log.error(
					LOG_PREFIX + "Erro ao tentar estabelecer a conexão."
				);
			}
		}
	};

	public static CadastroUnificadoService ws = null;

	static {

		try {
			tryConnection();
		} catch (Exception ex) {

			log.error(ex.getLocalizedMessage());
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
			Properties.clientId(),
			Properties.clientSecret(),
			Properties.portalSegurancaUrl(),
			Properties.cadastroUnificadoUrl()
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
		log.info(LOG_PREFIX + "Buscando Usuário: " + cpf);

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
		log.info(LOG_PREFIX + "Verificando se o Usuário do CPF: "
			+ cpf + " existe");

		return this.isUser(cpf);

	}


}
