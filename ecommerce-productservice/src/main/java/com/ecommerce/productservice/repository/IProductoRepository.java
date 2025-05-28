package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
