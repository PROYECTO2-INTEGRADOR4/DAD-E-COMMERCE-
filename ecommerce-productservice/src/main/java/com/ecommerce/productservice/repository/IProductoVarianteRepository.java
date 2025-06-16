package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.domain.ProductoVariante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoVarianteRepository extends JpaRepository<ProductoVariante, Long> {
    @Query("SELECT pv FROM ProductoVariante pv " +
           "JOIN FETCH pv.varianteOpciones vo " +
           "JOIN FETCH vo.opcion " +
           "WHERE pv.id = :id")
    Optional<ProductoVariante> findByIdWithOpciones(@Param("id")Long id);
}
