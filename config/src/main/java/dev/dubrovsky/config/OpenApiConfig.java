package dev.dubrovsky.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Online Store Api",
                description = "Этот API предоставляет конечные точки для управления онлайн магазином.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Dubrovsky Nikita",
                        email = "devdubrovsky@gmail.com"
                )
        )
)
@Configuration
public class OpenApiConfig {

}
