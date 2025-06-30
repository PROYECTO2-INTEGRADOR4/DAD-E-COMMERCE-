package com.ecomerce.notificationservice.controller;



import com.ecomerce.notificationservice.dto.SmsRequestDto;
import com.ecomerce.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestBody SmsRequestDto smsRequestDto) {
        notificationService.sendSms(smsRequestDto);
        return ResponseEntity.ok("SMS enviado correctamente");
    }
}