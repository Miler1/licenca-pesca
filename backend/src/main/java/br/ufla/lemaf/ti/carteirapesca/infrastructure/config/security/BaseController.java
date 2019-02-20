package br.ufla.lemaf.ti.carteirapesca.infrastructure.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class BaseController implements ErrorController {

//	@Value("${server.error.path}")
	private static String errorPath;

	@Value("${spring.security.entradaunica.portal-seguranca.url}")
	private String urlEntradaUnica;

	private boolean debug = true;

	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {

		return errorPath;
	}

//	@RequestMapping("${server.error.path}")
	void error(WebRequest webRequest, HttpServletResponse response) {

		Map<String, Object> props = getErrorAttributes(webRequest, this.debug);
		Integer status = Integer.parseInt(props.get("status").toString());

		if (status == 403) {
			throw new AccessDeniedException("");
		}

	}

	private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

		return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);

	}

}
