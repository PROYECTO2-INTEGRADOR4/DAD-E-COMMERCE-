package com.ecommerce_envioservice.service.impl;

<<<<<<< HEAD
=======
import com.ecommerce_envioservice.dto.SeguimientoDto;
>>>>>>> 596dad4 (Cambios envios probado)
import com.ecommerce_envioservice.entity.Seguimiento;
import com.ecommerce_envioservice.repository.SeguimientoRepository;
import com.ecommerce_envioservice.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public Seguimiento create(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public Seguimiento update(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public void delete(Long id) {
        seguimientoRepository.deleteById(id);
    }

    @Override
    public Optional<Seguimiento> read(Long id) {
        return seguimientoRepository.findById(id);
    }

    @Override
    public List<Seguimiento> readAll() {
        return seguimientoRepository.findAll();
    }
<<<<<<< HEAD
}
=======

    public SeguimientoDto convertToSeguimientoDTO(Seguimiento seguimiento) {
        SeguimientoDto dto = new SeguimientoDto();
        dto.setId(seguimiento.getId());
        dto.setEstado(seguimiento.getEstado());
        dto.setUbicacionActual(seguimiento.getUbicacionActual());
        dto.setObservaciones(seguimiento.getObservaciones());
        dto.setFechaHoraRegistro(seguimiento.getFechaHoraRegistro());
        if (seguimiento.getEnvio() != null) {
            dto.setEnvioId(seguimiento.getEnvio().getId());
        }
        return dto;
}
}

>>>>>>> 596dad4 (Cambios envios probado)
