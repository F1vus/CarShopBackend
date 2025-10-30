package edu.team.carshopbackend.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("CarShop SprintBoard API")
                        .description("Dokumentacja API dla projektu zespołowego CarShopSprintBoard")
                        .version("1.0.0"));
    }
}
