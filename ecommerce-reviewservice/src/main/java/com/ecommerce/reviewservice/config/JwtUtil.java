package com.ecommerce.reviewservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Component
public class JwtUtil {

    // ✅ Usa EXACTAMENTE la misma clave que tu microservicio de usuarios
    private static final String SECRET_KEY = "aW5nc2lzdGVtYXMyMDIzb2xhb2xhZGFyZWx5bm9lbWFyeQ==";

    private static Key getSignKey() {
        // ✅ Agrega este log temporal para verificar la clave en consola
        System.out.println("SECRET_KEY usada en REVIEW SERVICE: " + SECRET_KEY);

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public static Long getUserId(String token) {
        Claims claims = extractAllClaims(token);
        Integer userId = claims.get("userId", Integer.class);
        return userId != null ? userId.longValue() : null;
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRoles(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("role");
    }
}