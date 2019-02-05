package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("br.ufla.lemaf.ti.carteirapesca")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(
		AuthenticationManagerBuilder auth) throws Exception {

		auth.
			authenticationProvider(authProvider);
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)

		throws Exception {

		auth.

			inMemoryAuthentication()

			.withUser("user").password("user").roles("USER");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers()
			.frameOptions()
			.disable()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/api/security/**").hasRole("USER")
			.antMatchers("/api/*", "/*","/content/*", "/index.html", "/login", "/public/**")
			.permitAll()
//                .anyRequest()
//                .authenticated()
			.and()
			.formLogin()
			.loginPage("/api/login")
//			.successHandler(new SuccessHandler(secureService))
			.successHandler(new SuccessHandler())
			.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
			.logout()
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID", "io")
			.logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
			.logoutUrl("/api/logout");
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
