package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.config.JwtUtil;
import com.ecommerce.reviewservice.domain.Review;
import com.ecommerce.reviewservice.service.ReviewService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    // ✅ Listar todos
    @GetMapping
    public ResponseEntity<List<Review>> readAll() {
        List<Review> reviews = service.readAll();
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        return service.read(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear nuevo (extrae userId del token)
    @PostMapping
    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody Review review) {
        try {
            Long userId = JwtUtil.getUserId(request.getHeader("Authorization"));
            review.setUsuarioId(userId); // asegúrate de tener campo usuarioId en Review
            Review saved = service.create(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Producto no válido: " + ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    // ✅ Actualizar existente: solo el dueño puede
    @PutMapping("/{id}")
    public ResponseEntity<Review> update(
            @PathVariable Long id,
            @Valid @RequestBody Review reviewRequest,
            @RequestHeader("Authorization") String authHeader) {

        Optional<Review> existing = service.read(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Long currentUserId = JwtUtil.getUserId(authHeader);
        if (currentUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<String> roles = JwtUtil.getRoles(authHeader);
        Review existingReview = existing.get();

        // ✅ Solo dueño o admin puede editar
        if (!existingReview.getUsuarioId().equals(currentUserId)
                && !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            Review updated = service.updatePartial(id, reviewRequest);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        Optional<Review> existing = service.read(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Long currentUserId = JwtUtil.getUserId(authHeader);
        if (currentUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<String> roles = JwtUtil.getRoles(authHeader);
        Review existingReview = existing.get();

        // ✅ Solo dueño o admin puede borrar
        if (!existingReview.getUsuarioId().equals(currentUserId)
                && !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}