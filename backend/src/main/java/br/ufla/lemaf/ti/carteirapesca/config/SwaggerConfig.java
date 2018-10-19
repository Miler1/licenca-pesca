package br.ufla.lemaf.ti.carteirapesca.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
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
 * Configuração do Swagger para documentação de REST-API.
 *
 * Detalhes:
 * - http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 * - https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.or(
						PathSelectors.regex("/api.*")
				))
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(new BasicAuth("basicAuth")));
	}

	/**
	 * Configuração de informação da API da aplicação.
	 *
	 * @return o objeto de informações da API
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Sistema de cadastro para carteira de perca do estado do AM",
				"<p>Rotas REST para o sistema. Cliquem em <b>application-controller</b> para ver os metodos.</p>",
				Config.APPLICATION_VERSION,
				null,
				new Contact("Highlander Paiva", "", "highlanderpaiva.lemaf@gmail.com"),
				null,
				null,
				Collections.emptyList()
		);
	}

}
