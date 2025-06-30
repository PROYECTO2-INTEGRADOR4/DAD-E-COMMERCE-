package com.ecommerce_envioservice.service.impl;

<<<<<<< HEAD
=======
import com.ecommerce_envioservice.dto.EnvioDto;
>>>>>>> 596dad4 (Cambios envios probado)
import com.ecommerce_envioservice.entity.Envio;
import com.ecommerce_envioservice.repository.EnvioRepository;
import com.ecommerce_envioservice.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public Envio create(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public Envio update(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public void delete(Long id) {
        envioRepository.deleteById(id);
<<<<<<< HEAD

=======
>>>>>>> 596dad4 (Cambios envios probado)
    }

    @Override
    public Optional<Envio> read(Long id) {
        return envioRepository.findById(id);
    }

    @Override
    public List<Envio> readAll() {
        return envioRepository.findAll();
    }
<<<<<<< HEAD
=======

    public EnvioDto convertToEnvioDTO(Envio envio) {
        EnvioDto dto = new EnvioDto();
        dto.setId(envio.getId());
        dto.setDireccionDestino(envio.getDireccionDestino());
        dto.setEstado(envio.getEstado());
        dto.setFechaEnvio(envio.getFechaEnvio());
        dto.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
        if (envio.getTransportista() != null) {
            dto.setTransportistaId(envio.getTransportista().getId());
        }
        return dto;
    }

>>>>>>> 596dad4 (Cambios envios probado)
}
