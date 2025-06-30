package com.ecommerce.gateway.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    @PostConstruct
    public void init() {
        System.out.println(">> [JWT Filter] ¡Filtro registrado correctamente!");
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(">> [JWT Filter] Método FILTER INVOCADO.");
        String path = exchange.getRequest().getURI().getPath();
        System.out.println(">> [JWT Filter] Request intercepted: " + path);

        if (path.startsWith("/auth")) {
            System.out.println(">> [JWT Filter] Ruta pública, sin validación JWT.");
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println(">> [JWT Filter] Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println(">> [JWT Filter] Header faltante o mal formado.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        System.out.println(">> [JWT Filter] Token extraído: " + token);

        try {
            jwtUtil.validateToken(token);
            System.out.println(">> [JWT Filter] Token válido.");

            String userId = jwtUtil.getUserIdFromToken(token);
            List<String> role = jwtUtil.getRoleFromToken(token);
            System.out.println(">> [JWT Filter] userId desde token: " + userId);

            if (userId == null || userId.isBlank()) {
                System.out.println(">> [JWT Filter] userId no presente en token.");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .headers(httpHeaders -> {
                        httpHeaders.remove(HttpHeaders.AUTHORIZATION);
                        httpHeaders.add("userId", userId);
                        httpHeaders.add("role", String.join(",", role));
                    })
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (Exception e) {
            System.out.println(">> [JWT Filter] Error al validar el token: " + e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
