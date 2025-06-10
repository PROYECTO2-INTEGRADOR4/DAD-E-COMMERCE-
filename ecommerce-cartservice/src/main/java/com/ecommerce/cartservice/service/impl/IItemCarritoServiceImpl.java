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
    public ItemCarrito addItemCarrito(Long carritoId, Long productoVarianteId, Integer cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        ProductoVarianteDto variante = productClient.getVariantesForId(productoVarianteId);
        BigDecimal precio = variante.getPrecio();

        ItemCarrito item = new ItemCarrito();
        item.setCarrito(carrito);
        item.setProductoVarianteId(productoVarianteId);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(precio);
        item.setEstado("A");

        return repository.save(item);
    }

    @Override
    public List<ItemCarritoResponseDto> listarItemsPorCarritoId(Long carritoId) {
        List<ItemCarrito> items = repository.findByCarritoId(carritoId);
        List<ItemCarritoResponseDto> respuesta = new ArrayList<>();
        for (ItemCarrito item : items) {
            ProductoVarianteDto variante = productClient
                    .getVariantesForId(item.getProductoVarianteId());
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
