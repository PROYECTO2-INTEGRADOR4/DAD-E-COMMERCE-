package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.ProductoVariante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoVarianteRepository extends JpaRepository<ProductoVariante, Long> {
}
