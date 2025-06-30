package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.domain.ItemOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrdenRepository extends JpaRepository<ItemOrden, Long> {
}
