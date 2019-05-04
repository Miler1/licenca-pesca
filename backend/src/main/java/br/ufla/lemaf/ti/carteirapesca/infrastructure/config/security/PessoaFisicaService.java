package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import main.java.br.ufla.lemaf.OAuthClientCadastroUnificado;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PessoaFisicaService {
	@Value("${spring.security.entradaunica.cadastro-unificado.url}")
	private String urlCadastroUnificado;

	public Pessoa findByUsuario(Usuario usuario) {

		String requestUrl = urlCadastroUnificado + "/external/pessoaFisica/buscarPorCpf/" + usuario.login;

		Pessoa pessoa;
		pessoa = WebServiceUtils.webServiceEU().executeRequestGet(requestUrl, Pessoa.class);

		return pessoa;

	}


}
