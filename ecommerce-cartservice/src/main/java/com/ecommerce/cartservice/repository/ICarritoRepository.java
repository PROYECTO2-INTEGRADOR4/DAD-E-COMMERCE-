package com.ecommerce.cartservice.repository;

import com.ecommerce.cartservice.domain.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUserId(Long userId);
}
