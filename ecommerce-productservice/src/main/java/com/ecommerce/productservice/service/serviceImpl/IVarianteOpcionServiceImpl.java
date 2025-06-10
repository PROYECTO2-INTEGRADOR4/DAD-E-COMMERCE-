package com.ecommerce.productservice.service.serviceImpl;

import com.ecommerce.productservice.domain.VarianteOpcion;
import com.ecommerce.productservice.repository.IVarianteOpcionRepository;
import com.ecommerce.productservice.service.IVarianteOpcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IVarianteOpcionServiceImpl implements IVarianteOpcionService {
    private final IVarianteOpcionRepository repository;
    @Override
    public VarianteOpcion create(VarianteOpcion vo) {
        return repository.save(vo);
    }

    @Override
    public VarianteOpcion update(VarianteOpcion vo) {
        return repository.save(vo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<VarianteOpcion> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<VarianteOpcion> readAll() {
        return repository.findAll();
    }
}
