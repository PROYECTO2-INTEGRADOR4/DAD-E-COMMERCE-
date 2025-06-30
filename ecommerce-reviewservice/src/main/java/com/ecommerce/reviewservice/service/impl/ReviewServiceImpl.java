package com.ecommerce.reviewservice.service.impl;

import com.ecommerce.reviewservice.ProductoClient.ProductoClient;
import com.ecommerce.reviewservice.domain.Review;
import com.ecommerce.reviewservice.dto.ProductoDto;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import com.ecommerce.reviewservice.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ProductoClient productClient; // 👈 inyectas el FeignClient

    @Override
    public Review create(Review review) {

        validateRating(review.getCalificacion());
        // Lógica para validar el ID del producto:
        try {
            ProductoDto producto = productClient.findById(review.getProductoId());
            if (producto == null) {
                throw new RuntimeException("Producto con ID " + review.getProductoId() + " no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Producto con ID " + review.getProductoId() + " no existe");
        }

        // Si existe, guarda la review:
        return repository.save(review);
    }

    @Override
    public Review updatePartial(Long id, Review reviewRequest) {
        Review existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review no encontrado"));

        validateRating(reviewRequest.getCalificacion());

        // Solo campos que quieres actualizar
        existing.setCalificacion(reviewRequest.getCalificacion());
        existing.setComentario(reviewRequest.getComentario());

        // La fecha SI se actualiza a ahora()
        existing.setFechaPublicacion(java.time.LocalDateTime.now());

        return repository.save(existing);
    }


    private void validateRating(BigDecimal calificacion) {
        if (calificacion == null) {
            throw new IllegalArgumentException("La calificación no puede ser nula");
        }

        // Rango permitido 0.0 - 5.0
        if (calificacion.compareTo(BigDecimal.ZERO) < 0 || calificacion.compareTo(new BigDecimal("5.0")) > 0) {
            throw new IllegalArgumentException("La calificación debe estar entre 0.0 y 5.0");
        }

        // Debe ser múltiplo de 0.5 (0.5, 1.0, 1.5, ..., 5.0)
        BigDecimal remainder = calificacion.remainder(new BigDecimal("0.5"));
        if (remainder.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("La calificación solo puede tener incrementos de 0.5");
        }
    }


@Override
    public void delete(Long id) {
        repository.deleteById(id);
    }



    @Override
    public Optional<Review> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Review> readAll() {
        return repository.findAll();
    }
}
