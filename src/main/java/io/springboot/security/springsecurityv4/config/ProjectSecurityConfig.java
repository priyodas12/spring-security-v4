package io.springboot.security.springsecurityv4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain customizedSecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/register/customer","/contact-us","/public-notice").permitAll();

        http.authorizeRequests()
                        .antMatchers("/my-account").hasAuthority("VIEW_ACCOUNT")
                        .antMatchers("/my-balance").hasAnyAuthority("VIEW_ACCOUNT","VIEW_BALANCE")
                        .antMatchers("/my-card").hasAuthority("VIEW_CARD")
                        .antMatchers("/my-loan").hasAnyAuthority("VIEW_LOAN","VIEW_ACCOUNT")
                        .anyRequest()
                                .authenticated();


        return http.build();
    }
    //approach-1-InMemoryAuthentication
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

    //approach-2-InMemoryAuthentication
   /* @Bean
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
    }*
    */

    /*
    Through JDBC JdbcUserDetailsManager, required spring defined users & authorities table
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    PasswordEncoder passwordEncoder(){
        /*return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                log.info("encoding...");
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                log.info("matching...");
                return rawPassword.toString().matches(encodedPassword);
            }
        };*/
        return new BCryptPasswordEncoder(12);
    }

}
