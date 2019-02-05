package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	private RequestCache requestCache = new HttpSessionRequestCache();


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {

//			CustomObjectMapper customObjectMapper = new CustomObjectMapper();
//			clearAuthenticationAttributes(request);
//
//			Usuario usuario =  secureService.findUsuarioByCpf(authentication.getPrincipal().toString());
//
//			UsuarioDTO usuarioDTO = new UsuarioDTO();
//
//			usuarioDTO.setUsuario(usuario);
//
//
//			MensagemRetorno<UsuarioDTO> mensagemRetorno = new MensagemRetorno<UsuarioDTO>(Mensagem.get("autenticacao.sucesso"), usuarioDTO);
//
//			response.getOutputStream().write(customObjectMapper.writeValueAsBytes(mensagemRetorno));
//
//			response.setHeader("Content-type", "application/json");

			return;

		}

		String targetUrlParam = getTargetUrlParameter();

		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {

			requestCache.removeRequest(request, response);

			clearAuthenticationAttributes(request);

			return;

		}

		clearAuthenticationAttributes(request);

	}
}
