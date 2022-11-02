package io.springboot.security.springsecurityv4.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.springboot.security.springsecurityv4.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> jwtToken = Optional.ofNullable(request.getHeader(JwtUtil.SECURITY_HEADER));
        String tokenVal=jwtToken.get();
        logger.info("Extracted jwtToken : "+tokenVal);
        try {

            SecretKey key = Keys.hmacShaKeyFor(JwtUtil.AUTH_KEY.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken.get())
                    .getBody();

            String username = String.valueOf(claims.get(JwtUtil.USERNAME));

            String authorities = (String) claims.get(JwtUtil.AUTHORITIES);

            log.info("Extracted username :{}, authorities: {}",username,authorities);

            Authentication auth = new UsernamePasswordAuthenticationToken(username,
                    null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

            SecurityContextHolder.getContext().setAuthentication(auth);

        }catch(Exception e){
            throw new BadCredentialsException("Invalid Credentials!");
        }
    filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/jwt/token");
    }
}
