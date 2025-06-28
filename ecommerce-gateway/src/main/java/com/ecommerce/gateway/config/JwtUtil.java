package com.ecommerce.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private SecretKey key;
    private JwtParser parser;

    @PostConstruct
    private void init() {
        String secret = "aW5nc2lzdGVtYXMyMDIzb2xhb2xhZGFyZWx5bm9lbWFyeQ==";
        byte[] decoded = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(decoded);
        parser = Jwts.parser().verifyWith(key).build();
    }

    public void validateToken(String token) {
        parser.parseSignedClaims(token); // lanza excepción si es inválido
    }

    public String getUserIdFromToken(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        Object userId = claims.get("userId");
        return userId != null ? userId.toString() : null;
    }

}
