package com.example.tenure_backend.jwt;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "djfjdljfkldfkljdfkla";
    private Long accessTokenTime = 3600L;

    private final UserDetailsService userDetailsService;

    public String generateToken(String userId) {
        return Jwts.builder()
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
            .setSubject(userId)
            .setIssuedAt(new Date())
            .setHeaderParam("typ", "access")
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails details = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            getBody(token).getExpiration().after(new Date());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
    }

    public String getUserName(String token) {
        return getBody(token).getSubject();
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
    }

}
