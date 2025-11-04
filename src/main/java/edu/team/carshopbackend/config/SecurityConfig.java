package edu.team.carshopbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity security) throws Exception
    {
       return security.csrf(AbstractHttpConfigurer::disable).cors(
               httpSecurityCorsConfigurer ->
                       httpSecurityCorsConfigurer.configurationSource(request ->
                               new CorsConfiguration().applyPermitDefaultValues())

       ).build();
    }
}


