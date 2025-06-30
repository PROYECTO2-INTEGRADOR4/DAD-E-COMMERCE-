package com.ecommerce.orderservice.service.serviceImpl;

import com.ecommerce.orderservice.client.CartClient;
import com.ecommerce.orderservice.domain.EstadoOrden;
import com.ecommerce.orderservice.domain.Orden;
import com.ecommerce.orderservice.dto.CrearOrdenRequest;
import com.ecommerce.orderservice.dto.ItemCarritoResponseDto;
import com.ecommerce.orderservice.repository.EstadoOrdenRepository;
import com.ecommerce.orderservice.repository.ItemOrdenRepository;
import com.ecommerce.orderservice.repository.OrdenRepository;
import com.ecommerce.orderservice.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository repository;
    private final CartClient cartClient;
    private final ItemOrdenRepository itemOrdenRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;
    @Override
    public Orden create(Orden o) {
        return repository.save(o);
    }

    @Override
    public Orden update(Orden o) {
        return repository.save(o);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Orden> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Orden> readAll() {
        return repository.findAll();
    }

    @Override
    public Orden crearOrden(CrearOrdenRequest request, Long userId) {
        EstadoOrden estado = estadoOrdenRepository.findByNombre("PENDIENTE").orElseThrow(() -> new RuntimeException("Estado 'PENDIENTE' no encontrado"));

        Orden orden = new Orden();
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setUserId(userId);
        orden.setEstadoOrden(estado);

        return repository.save(orden);
    }
}
