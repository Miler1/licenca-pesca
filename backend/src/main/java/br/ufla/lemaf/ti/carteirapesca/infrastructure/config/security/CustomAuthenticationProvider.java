package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();


//		try {
//			secureService.doLogin(name, password);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return null;
//		}

		String roles = "ROLE_USER";
		List<GrantedAuthority> grantedAuths =
			AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

		return new UsernamePasswordAuthenticationToken(
			name, password,grantedAuths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
			UsernamePasswordAuthenticationToken.class);
	}
}
