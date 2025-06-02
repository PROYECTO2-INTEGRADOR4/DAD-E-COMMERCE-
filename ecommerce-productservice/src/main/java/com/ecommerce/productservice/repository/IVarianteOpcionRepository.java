package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.VarianteOpcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVarianteOpcionRepository extends JpaRepository<VarianteOpcion, Long> {
}
