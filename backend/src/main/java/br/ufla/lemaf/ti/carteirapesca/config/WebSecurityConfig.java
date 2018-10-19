package br.ufla.lemaf.ti.carteirapesca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configurações de segurança da aplicação.
 *
 * @see <a href="https://spring.io/guides/gs/securing-web/">
 * Spring: Getting Started Guide "Securing a Web Application"</a>
 * Para definir usuários com acesso básico de autenticação
 * e para acessar URIs controladas.
 *
 * @author Highlander Paiva
 * @since 0.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configuração de proteção de rotas REST
	 *
	 * @param httpSecurity Objeto de segurança HTTP
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeRequests()
				.antMatchers("/").permitAll()
				// Para o Swagger UI. Ver http://springfox.github.io/springfox/docs/current/#answers-to-common-questions-and-problems
				// Número 26: "Why is http://host:port/swagger-ui.html blank"
				.antMatchers(
						HttpMethod.GET,
						"/v2/api-docs",
						"/swagger-resources/**",
						"/swagger-ui.html**",
						"/webjars/**",
						"favicon.ico"
				).permitAll()
				.anyRequest().authenticated()
				.and().httpBasic()
				// Para REST services desativa proteção CSRF
				// Ver https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#when-to-use-csrf-protection
				.and().csrf().disable();
	}
}
