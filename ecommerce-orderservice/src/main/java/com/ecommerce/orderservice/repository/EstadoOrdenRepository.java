package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.domain.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Long> {
    Optional<EstadoOrden> findByNombre(String nombre);
}
