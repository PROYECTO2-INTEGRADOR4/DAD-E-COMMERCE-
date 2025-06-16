package com.ecommerce_paymentservice.client;
import com.ecommerce_paymentservice.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-user")
public interface UsuarioClient {
    @GetMapping("/api/usuarios/{id}")
    UsuarioDto obtenerUsuarioPorId(@PathVariable("id") Long id);
}
