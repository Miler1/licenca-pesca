package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private String successRedirectUrl;

	public AuthenticationFilter(String successRedirectUrl) {

		super(new AntPathRequestMatcher("/login/**"));

		this.successRedirectUrl = successRedirectUrl;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, final FilterChain chain) throws IOException
		, ServletException {

		//On success redirect
		this.setAuthenticationSuccessHandler((request1, response1, authentication) -> response1.sendRedirect(request1.getContextPath() + successRedirectUrl));

		super.doFilter(request, response, chain);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		final String tokenValue = getTokenValue(request);

		if (StringUtils.isEmpty(tokenValue)) {
			//Doing this check is kinda dumb because we check for it up above in doFilter
			//..but this is a public method and we can't do much if we don't have the header
			//also we can't do the check only here because we don't have the chain available
			return null;
		}

		AuthenticationToken token = new AuthenticationToken(tokenValue);
		token.setDetails(authenticationDetailsSource.buildDetails(request));

		return this.getAuthenticationManager().authenticate(token);

	}

	private String getTokenValue(HttpServletRequest request) {

		String[] paths = request.getRequestURI().split("/login/");

		if (paths.length >= 2) {
			return paths[1];
		}

		return null;

	}


}
