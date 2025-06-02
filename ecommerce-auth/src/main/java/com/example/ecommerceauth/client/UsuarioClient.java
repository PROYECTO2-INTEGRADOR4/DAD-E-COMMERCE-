package com.example.ecommerceauth.client;

import com.example.ecommerceauth.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-user", url = "localhost:8090/api/usuario")
public interface UsuarioClient {

    @GetMapping("/by-username/{username}")
    List<UsuarioDto> findAllUsersByUsername(@PathVariable String username);
}
