package br.com.irvabank.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setDescription("Server URL in Development environment");

        Server stageServer = new Server();
        stageServer.setDescription("Server URL in Staging environment");

        Server prodServer = new Server();
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("irvabank@irvabank.com.br");
        contact.setName("IrvaBank");
        contact.setUrl("irvabank.com.br");


        Info info = new Info()
                .title("IrvaBank API")
                .version("1.0")
                .contact(contact)
                .description("API that provides the services of Irvabank.")
                .termsOfService("https://www.irvabank.com.br");

        return new OpenAPI().info(info).servers(List.of(devServer, stageServer,  prodServer));
    }
}

