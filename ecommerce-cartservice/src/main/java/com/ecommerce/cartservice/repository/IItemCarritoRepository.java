package com.ecommerce.cartservice.repository;

import com.ecommerce.cartservice.domain.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
}
