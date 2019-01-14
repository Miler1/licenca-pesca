package br.ufla.lemaf.ti.carteirapesca.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
public class ThymeleafConfiguration {
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver(){
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templatesPDF/");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("XHTML");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}
}
