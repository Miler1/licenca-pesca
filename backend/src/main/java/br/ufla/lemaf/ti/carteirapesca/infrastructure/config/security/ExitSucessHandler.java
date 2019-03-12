package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;


import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExitSucessHandler implements LogoutSuccessHandler {


	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

		httpServletResponse.sendRedirect(Properties.portalSegurancaUrl() + "#/home");

	}
}
