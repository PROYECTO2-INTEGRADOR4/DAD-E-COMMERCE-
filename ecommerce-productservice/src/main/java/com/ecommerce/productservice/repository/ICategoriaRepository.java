package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
