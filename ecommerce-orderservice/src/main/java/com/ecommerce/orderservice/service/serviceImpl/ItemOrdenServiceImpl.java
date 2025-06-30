package com.ecommerce.orderservice.service.serviceImpl;

import com.ecommerce.orderservice.domain.ItemOrden;
import com.ecommerce.orderservice.repository.ItemOrdenRepository;
import com.ecommerce.orderservice.service.ItemOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemOrdenServiceImpl implements ItemOrdenService {

    private final ItemOrdenRepository repository;
    @Override
    public ItemOrden create(ItemOrden io) {
        return repository.save(io);
    }

    @Override
    public ItemOrden update(ItemOrden io) {
        return repository.save(io);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ItemOrden> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ItemOrden> readAll() {
        return repository.findAll();
    }
}
