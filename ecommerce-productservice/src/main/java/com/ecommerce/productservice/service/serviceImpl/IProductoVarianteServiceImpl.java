package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.ProductoVariante;
import com.ecommerce.productservice.domain.VarianteOpcion;
import com.ecommerce.productservice.dto.ProductoVarianteDto;
import com.ecommerce.productservice.repository.IProductoVarianteRepository;
import com.ecommerce.productservice.service.IProductoVarianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IProductoVarianteServiceImpl implements IProductoVarianteService {
    private final IProductoVarianteRepository repository;
    @Override
    public ProductoVariante create(ProductoVariante pv) {
        return repository.save(pv);
    }

    @Override
    public ProductoVariante update(ProductoVariante pv) {
        return repository.save(pv);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductoVariante> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProductoVariante> readAll() {
        return repository.findAll();
    }

    @Override
    public ProductoVarianteDto readProductoVarianteforId(Long id) {
        ProductoVariante pv = repository.findByIdWithOpciones(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id " + id));

        Map<String, String> atributos = new HashMap<>();
        System.out.println("VarianteOpciones: " + pv.getVarianteOpciones().size());
        for (VarianteOpcion vo: pv.getVarianteOpciones()) {
            String tipo = vo.getOpcion().getNombre();
            String valor = vo.getValor();
            atributos.put(tipo, valor);
        }

        return new ProductoVarianteDto(
                pv.getId(),
                pv.getProducto().getNombre(),
                atributos,
                pv.getPrecio()
        );
    }
}
