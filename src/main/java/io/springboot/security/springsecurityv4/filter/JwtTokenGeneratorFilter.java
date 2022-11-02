package io.springboot.security.springsecurityv4.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.security.Keys;
import io.springboot.security.springsecurityv4.JwtUtil;
import io.springboot.security.springsecurityv4.model.JwtToken;
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
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    private Gson gson = new Gson();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            SecretKey key= Keys.hmacShaKeyFor(JwtUtil.AUTH_KEY.getBytes(StandardCharsets.UTF_8));

            Date expireTime=new Date(new Date().getTime()+3600000);

            String jwtToken=Jwts.builder()
                    .setIssuer("auth-server")
                    .setSubject("test-jwt-token")
                    .claim(JwtUtil.USERNAME,auth.getName())
                    .claim(JwtUtil.AUTHORITIES,accumulateAuthorities(auth.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(expireTime)
                    .signWith(key)
                    .compact();
            log.info("Generated jwt token : {}",jwtToken);

        /*    HttpServletResponse response1=response;
            response1.
            JwtToken token=new JwtToken();
            token.setToken(jwtToken);
            token.setExpireTime(expireTime.toString());

            PrintWriter out = response.getWriter();
            ObjectMapper objectMapper= new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(token);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();*/

            response.setHeader(JwtUtil.SECURITY_HEADER,jwtToken);
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
