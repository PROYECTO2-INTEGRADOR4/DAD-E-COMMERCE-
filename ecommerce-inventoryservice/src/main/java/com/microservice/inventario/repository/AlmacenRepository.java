package com.microservice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.inventario.entity.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
