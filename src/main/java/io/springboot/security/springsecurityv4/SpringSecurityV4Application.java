package io.springboot.security.springsecurityv4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories("io.springboot.security.springsecurityv4.repository")
@EntityScan("io.springboot.security.springsecurityv4.model")
public class SpringSecurityV4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityV4Application.class, args);
	}

}
