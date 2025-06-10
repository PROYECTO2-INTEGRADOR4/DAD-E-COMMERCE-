package com.microservice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.inventario.entity.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
