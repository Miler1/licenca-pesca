package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import main.java.br.ufla.lemaf.beans.pessoa.Perfil;
import main.java.br.ufla.lemaf.beans.pessoa.Permissao;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import main.java.br.ufla.lemaf.beans.pessoa.Usuario;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for holding the raw token value and - if authenticated - the {@link Usuario}.
 */
@SuppressWarnings({"squid:S2160"})
public class AuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private final String token;

	private final Usuario user;

	private final Pessoa pessoa;

	AuthenticationToken(String token) {

		super(null);

		this.token = token;
		this.user = null;
		this.pessoa = null;

		setAuthenticated(false);

	}

	AuthenticationToken(String token, Usuario user, Pessoa pessoa) {

		super(getGrantedAuthorities(user));

		this.token = token;
		this.user = user;
		this.pessoa = pessoa;

		setAuthenticated(true);

	}

	private static List<GrantedAuthority> getGrantedAuthorities(Usuario usuario) {

		Optional<Perfil> perfil =
			usuario.perfis.stream().filter(p -> p.nome.equals(usuario.perfilSelecionado.nome)).findFirst();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		if (perfil.isPresent() && (perfil.get().permissoes != null) && !perfil.get().permissoes.isEmpty()) {

			for (Permissao permissao : perfil.get().permissoes) {

				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissao.codigo);

				grantedAuthorities.add(grantedAuthority);

			}

		}

		return grantedAuthorities;

	}

	@Override
	public Object getCredentials() {

		return getToken();
	}

	@Override
	public Object getPrincipal() {

		return getUser();
	}

	public String getToken() {

		return this.token;
	}

	public Usuario getUser() {

		return this.user;
	}

	public Pessoa getPessoa() {

		return this.pessoa;
	}

}
