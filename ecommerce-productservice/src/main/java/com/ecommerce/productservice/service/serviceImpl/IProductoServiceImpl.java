package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.Producto;
import com.ecommerce.productservice.dto.ProductoCatalogoDto;
import com.ecommerce.productservice.repository.IProductoRepository;
import com.ecommerce.productservice.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IProductoServiceImpl implements IProductoService {
    private final IProductoRepository repository;
    @Override
    public Producto create(Producto p) {
        return repository.save(p);
    }

    @Override
    public Producto update(Producto p) {
        return repository.save(p);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Producto> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Producto> readAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductoCatalogoDto> obtenerProductoCatalogo() {
        List<Object[]> resultados = repository.obtenerCatalogoConColoresYPrecioMinimo();

        return resultados.stream().map(obj -> {
            Long productoId = ((Number) obj[0]).longValue();
            String nombreProducto = (String) obj[1];
            BigDecimal precioMinimo = (BigDecimal) obj[2];
            String imagenPrincipal = (String) obj[3];
            String colores = (String) obj[4];
            List<String> coloresList = colores != null ? Arrays.asList(colores.split(",\\s*")) : new ArrayList<>();

            return new ProductoCatalogoDto(productoId, nombreProducto, imagenPrincipal, precioMinimo, coloresList);
        }).toList();
    }
}
