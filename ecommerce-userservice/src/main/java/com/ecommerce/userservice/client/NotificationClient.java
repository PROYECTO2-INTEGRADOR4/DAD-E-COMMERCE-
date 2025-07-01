package com.ecommerce.userservice.client;

import com.ecommerce.userservice.dto.NotificaciónRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-notification")
public interface NotificationClient {

    @PostMapping("/api/notification")
    void enviarNotificacion(@RequestBody NotificaciónRequestDto request);
}
