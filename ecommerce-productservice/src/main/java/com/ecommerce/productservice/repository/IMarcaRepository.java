package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcaRepository extends JpaRepository<Marca, Long> {
}
