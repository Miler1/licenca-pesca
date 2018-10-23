package br.ufla.lemaf.ti.carteirapesca.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuração do Swagger.
 *
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Mecanismo de configuração da API.
	 *
	 * @return {@link Docket}
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build()
			.pathMapping("/")
			.apiInfo(apiInfo())
			.securitySchemes(Collections.singletonList(new BasicAuth("BASIC_AUTH")));
	}

	/**
	 * Registro das informações da api.
	 *
	 * @return {@link ApiInfo}
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Sistema de Licença de Pesca do Amazônas",
			"<p>Sistema para retirar e consultar licenças de pesca.</p>",
			null,
			null,
			new Contact(
				"Highlander Paiva", null, "highlanderpaiva.lemaf@gmail.com"
			),
			null,
			null,
			Collections.emptyList()
		);
	}

}
