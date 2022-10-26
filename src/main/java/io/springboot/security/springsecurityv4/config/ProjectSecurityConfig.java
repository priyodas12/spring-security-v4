package io.springboot.security.springsecurityv4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain customizedSecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.httpBasic();
        http.authorizeRequests()
                        .antMatchers("/my-account","/my-balance","/my-card","/my-loan")
                                .authenticated()
                                        .antMatchers("/contact-us","/notice")
                                                .permitAll();

        return http.build();
    }
    //approach-1
/*    @Bean
    InMemoryUserDetailsManager getUserDetails(){
        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin1")
                .password("admin1")
                .authorities("admin")
                .build();
        UserDetails basicUser= User.withDefaultPasswordEncoder()
                .username("user1")
                .password("user1")
                .authorities("user")
                .build();
        return new InMemoryUserDetailsManager(admin,basicUser);
    }*/

    //approach-2
    @Bean
    InMemoryUserDetailsManager getUserDetailsWithNoOpPE(){
        UserDetails admin= User
                .withUsername("admin2")
                .password(bCryptPasswordEncoder().encode("admin2"))
                .authorities("admin")
                .build();
        UserDetails basicUser= User
                .withUsername("user2")
                .password(bCryptPasswordEncoder().encode("user2"))
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin,basicUser);
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
