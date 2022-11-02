package io.springboot.security.springsecurityv4.filter;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    private static final String AUTH_KEY="8x/A?D(G+KbPeShVmYq3t6w9z$B&E)H@";
    private static final String SECURITY_HEADER="Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            SecretKey key= Keys.hmacShaKeyFor(AUTH_KEY.getBytes(StandardCharsets.UTF_8));

            String jwtToken=Jwts.builder()
                    .setIssuer("auth-server")
                    .setSubject("test-jwt-token")
                    .claim("username",auth.getName())
                    .claim("authorities",accumulateAuthorities(auth.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+3600000))
                    .signWith(key)
                    .compact();
            log.info("Generated jwt token : {}",jwtToken);

            response.setHeader(SECURITY_HEADER,jwtToken);
        }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/contact-us");
    }

    private String accumulateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritySet=new HashSet<>();
        for(GrantedAuthority auth: authorities){
            authoritySet.add(auth.getAuthority());
        }
        log.info("Authority Accumulation: {}",String.join(",",authoritySet));
        return String.join(",",authoritySet);
    }
}
