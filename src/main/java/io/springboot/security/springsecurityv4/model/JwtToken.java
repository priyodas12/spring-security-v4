package io.springboot.security.springsecurityv4.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtToken {
    private String token;
    private String expireTime;
}
