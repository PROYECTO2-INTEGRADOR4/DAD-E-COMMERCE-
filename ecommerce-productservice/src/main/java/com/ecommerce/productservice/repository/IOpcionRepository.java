package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOpcionRepository extends JpaRepository<Opcion, Long> {
}
