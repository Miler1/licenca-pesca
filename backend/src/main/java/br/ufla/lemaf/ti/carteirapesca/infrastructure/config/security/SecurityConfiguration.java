package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;


import br.ufla.lemaf.ti.carteirapesca.domain.services.autenticacao.impl.AutenticacaoBuilderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.fakeAuthentication}")
	public Boolean fakeAuthentication;

	@Value("${spring.security.successRedirectUrl}")
	private String successRedirectUrl;

	@Value("${spring.security.entradaunica.portal-seguranca.url}")
	private String urlEntradaUnica;

	@Value("${spring.security.entradaunica.client-id}")
	private String clientIdEntradaUnica;

	@Value("${spring.security.entradaunica.client-secret}")
	private String clientSecretEntradaUnica;

	@Value("${spring.security.ipsAllowed}")
	private String ipsAllowed;

	private AutenticacaoBuilderImpl autenticacaoBuilder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		if (this.fakeAuthentication) {

			auth.inMemoryAuthentication()
				.withUser("fakeuser")
				.password("{noop}123")
				.roles("ADMIN");

		} else {

			auth.authenticationProvider(autenticacaoBuilder);

		}

	}

	// Note, we don't register this as a bean as we don't want it to be added to the
	// main Filter chain, just the spring security filter chain
	protected AbstractAuthenticationProcessingFilter createCustomFilter() throws Exception {

		AuthenticationFilter filter = new AuthenticationFilter(this.successRedirectUrl);

		filter.setAuthenticationManager(authenticationManagerBean());

		return filter;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http//.addFilterBefore(createCustomFilter(), AnonymousAuthenticationFilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.and()
				.headers()
					.frameOptions()
					.disable()
			.and()
			.csrf()
					.disable()
			.authorizeRequests()
				.antMatchers("*/api/**").permitAll()
				.antMatchers("*/external/**").access(externalIpsAllowed())
				.antMatchers("*/images/**").access(externalIpsAllowed())
				.antMatchers("/banco/lista-remessa", "/banco/lista-retornos", "/banco/download-retorno/", "/banco/upload-retorno", "/banco/download-remessa/", "/banco/gera-remessa" ).authenticated()
			.and()
			.logout()
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID", "io")
				.logoutUrl("/logout")
				.logoutSuccessHandler(new ExitSucessHandler())
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}

	private String externalIpsAllowed() {

		boolean first = true;
		StringBuilder builder = new StringBuilder();

		String[] listaIps = this.ipsAllowed.trim().split(",");
		for (String ip : listaIps) {

			if (!first)
				builder.append(" or ");

			builder.append("hasIpAddress('" + ip + "')");
			first = false;

		}

		return builder.toString();

	}


}
