package com.ecommerce_paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce_paymentservice.entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
