package com.alura.forumchallenge.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {
	
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(new Components()
	          .addSecuritySchemes("bearer-key",
	          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
              .info(new Info()
                      .title("Forum-HUB API")
                      .description("API Rest da aplicação Forum-HUB, contendo as funcionalidades de CRUD de um forum.")
                      .contact(new Contact()
                              .name("Otaviano Cruz")
                              .email("0t4v14n0@gmail.com"))
              .license(new License()
                      .name("GitHub")
                      .url("https://github.com/0t4v14n0/Challenge-ForumHub")));
	}

}
