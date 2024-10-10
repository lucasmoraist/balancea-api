package com.lucasmoraist.balancea.infra.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Balancea API")
                        .version("1.0")
                        .description("API para controle de finanças pessoais")
                        .summary("API para controle de finanças pessoais")
                        .contact(new Contact()
                                .name("Lucas de Morais Nascimento Taguchi")
                                .email("luksmnt1101@gmail.com")
                        )
                        .license(new License()
                                .name("MIT License")
                                .identifier("MIT")
                                .url("https://opensource.org/licenses/MIT")
                        )
                );
    }

}
