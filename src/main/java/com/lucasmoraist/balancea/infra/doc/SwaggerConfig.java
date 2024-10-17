package com.lucasmoraist.balancea.infra.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(this.info())
                .servers(List.of(this.localServer()))
                .components(this.components());
    }

    private Info info() {
        return new Info()
                .title("Balancea API")
                .version("1.1")
                .description("API para controle de finanças pessoais")
                .contact(contact())
                .license(license())
                ;
    }

    private Contact contact() {
        return new Contact()
                .name("Lucas de Morais Nascimento Taguchi")
                .email("luksmnt1101@gmail.com")
                .url("https://github.com/lucasmoraist");
    }

    private License license() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
    }

    private Server localServer() {
        return new Server()
                .description("Local")
                .url("http://localhost:8080");
    }

    private Components components() {
        return new Components().addSecuritySchemes("bearer-key", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );
    }

}
