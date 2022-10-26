package io.springboot.security.springsecurityv4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain customizedSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                        .antMatchers("/my-account","/my-balance","/my-card","/my-loan")
                                .authenticated()
                                        .antMatchers("/contact-us","/notice")
                                                .permitAll();

        http.formLogin();
        http.httpBasic();

        return http.build();
    }
}
