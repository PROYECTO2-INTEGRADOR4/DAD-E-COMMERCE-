package com.ecommerce.cartservice.service.impl;

import com.ecommerce.cartservice.client.ProductClient;
import com.ecommerce.cartservice.domain.Carrito;
import com.ecommerce.cartservice.domain.ItemCarrito;
import com.ecommerce.cartservice.dto.ItemCarritoResponseDto;
import com.ecommerce.cartservice.dto.ProductoVarianteDto;
import com.ecommerce.cartservice.repository.ICarritoRepository;
import com.ecommerce.cartservice.repository.IItemCarritoRepository;
import com.ecommerce.cartservice.service.IItemCarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IItemCarritoServiceImpl implements IItemCarritoService {
    private final IItemCarritoRepository repository;
    private final ICarritoRepository carritoRepository;
    private final ProductClient productClient;

    @Override
    public ItemCarrito create(ItemCarrito ic) {
        return repository.save(ic);
    }

    @Override
    public ItemCarrito update(ItemCarrito ic) {
        return repository.save(ic);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ItemCarrito> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ItemCarrito> readAll() {
        return repository.findAll();
    }

    @Override
    public ItemCarrito addItemCarrito(Long userId, Long productoVarianteId, Integer cantidad) {
        Carrito carrito = carritoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el Usuario"));
        ProductoVarianteDto variante = productClient.getVariantesForId(productoVarianteId);

        Optional<ItemCarrito> itemExistente = repository.findByCarritoIdAndProductoVarianteId(carrito.getId(), productoVarianteId);
        if (itemExistente.isPresent()) {
            ItemCarrito itemCarrito = itemExistente.get();
            itemCarrito.setCantidad(itemCarrito.getCantidad() + cantidad);
            return repository.save(itemCarrito);
        } else {
            ItemCarrito item = new ItemCarrito();
            item.setCarrito(carrito);
            item.setProductoVarianteId(productoVarianteId);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(variante.getPrecio());
            item.setEstado("A");

            return repository.save(item);
        }
    }

    @Override
    public List<ItemCarritoResponseDto> listarItemsPorCarritoId(Long userId) {
        Carrito carrito = carritoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el Usuario"));

        List<ItemCarrito> items = repository.findByCarritoId(carrito.getId());
        List<ItemCarritoResponseDto> respuesta = new ArrayList<>();

        for (ItemCarrito item : items) {
            ProductoVarianteDto variante = productClient.getVariantesForId(item.getProductoVarianteId());
            ItemCarritoResponseDto dto = new ItemCarritoResponseDto();
            dto.setId(item.getId());
            dto.setProductoVarianteId(item.getProductoVarianteId());
            dto.setNombreproducto(variante.getNombreproducto());
            dto.setAtributos(variante.getAtributos());
            dto.setCantidad(item.getCantidad());
            dto.setPrecioUnitario(item.getPrecioUnitario());

            respuesta.add(dto);
        }
        return respuesta;
    }
}
