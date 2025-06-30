package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
