package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.domain.ItemOrden;

import java.util.List;
import java.util.Optional;

public interface ItemOrdenService {
    ItemOrden create(ItemOrden io);
    ItemOrden update(ItemOrden io);
    void delete(Long id);
    Optional<ItemOrden> read(Long id);
    List<ItemOrden> readAll();
}
