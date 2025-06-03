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
    public void delete(ProductoVariante pv) {
        repository.delete(pv);
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
    public ProductoVarianteDto readVarianteforId(Long id) {
        ProductoVariante pv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id " + id));
        ProductoVarianteDto pvDto = new ProductoVarianteDto();
        pvDto.setId(pv.getId());
        pvDto.setNombreproducto(pv.getProducto().getNombre());
        pvDto.setPrecio(pv.getPrecio());

        Map<String, String> atributos = new HashMap<>();
        for (VarianteOpcion vo: pv.getVarianteOpciones()) {
            String tipo = vo.getOpcion().getNombre();
            String valor = vo.getValor();
            atributos.put(tipo, valor);
        }

        pvDto.setAtributos(atributos);
        return pvDto;
    }
}
