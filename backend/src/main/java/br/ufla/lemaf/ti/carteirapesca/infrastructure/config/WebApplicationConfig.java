package br.ufla.lemaf.ti.carteirapesca.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/notFound").setViewName("forward:/index.html");
	}

//	@Bean
//	public WebServerFactoryCustomizer containerCustomizer() {
//		return container -> {
//			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
//				"/notFound"));
//		};
//	}

}
