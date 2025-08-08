package com.movieflix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI(){

        Contact contact = new Contact();
        contact.name("Pedro");
        contact.email("pedrocatarino246@gmail.com");

        Info info = new Info();
        info.title("Movieflix");
        info.version("v1");
        info.description("Aplicação de gerenciamento de filme");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
