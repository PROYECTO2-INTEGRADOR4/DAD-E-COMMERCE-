package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtiquetaRepository extends JpaRepository<Etiqueta, Long> {
}
