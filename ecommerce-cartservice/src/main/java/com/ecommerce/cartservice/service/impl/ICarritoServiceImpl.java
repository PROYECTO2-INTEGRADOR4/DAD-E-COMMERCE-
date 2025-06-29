package com.ecommerce.cartservice.service.impl;

import com.ecommerce.cartservice.client.ProductClient;
import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.domain.ItemCarrito;
import com.ecommerce.cartservice.dto.ItemCarritoResponseDto;
import com.ecommerce.cartservice.dto.ProductoVarianteDto;
import com.ecommerce.cartservice.repository.ICarritoRepository;
import com.ecommerce.cartservice.repository.IItemCarritoRepository;
import com.ecommerce.cartservice.service.ICarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ICarritoServiceImpl implements ICarritoService {

    private final ICarritoRepository repository;
    private final IItemCarritoRepository repositoryItem;
    private final ProductClient productClient;
    @Override
    public Carrito create(Carrito c) {
        return repository.save(c);
    }

    @Override
    public Carrito update(Carrito c) {
        return repository.save(c);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Carrito> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Carrito> readAll() {
        return repository.findAll();
    }

    @Override
    public Carrito createCarritoForUser(Long userId) {
        Optional<Carrito> carrito = repository.findByUserId(userId);
        if (carrito.isPresent()) {
            return carrito.get();
        }
        Carrito c = new Carrito();
        c.setUserId(userId);
        c.setEstado("A");
        return repository.save(c);
    }

    @Override
    public Optional<Carrito> readCarritoForUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<ItemCarritoResponseDto> obtnerItemsPorIds(List<Long> itemIds, Long userId) {
        return repositoryItem.findAllById(itemIds).stream()
                .filter(item -> item.getCarrito().getUserId().equals(userId))
                .map(item -> {
                    ProductoVarianteDto variante = productClient.getVariantesForId(item.getProductoVarianteId());
                    ItemCarritoResponseDto dto = new ItemCarritoResponseDto();
                    dto.setId(item.getId());
                    dto.setProductoVarianteId(item.getProductoVarianteId());
                    dto.setNombreproducto(variante.getNombreproducto());
                    dto.setCantidad(item.getCantidad());
                    dto.setPrecioUnitario(item.getPrecioUnitario());
                    dto.setAtributos(variante.getAtributos());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
