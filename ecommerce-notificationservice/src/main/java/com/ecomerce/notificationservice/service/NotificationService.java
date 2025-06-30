package com.ecomerce.notificationservice.service;

import com.ecomerce.notificationservice.dto.SmsRequestDto;

public interface NotificationService {
    void sendSms(SmsRequestDto smsRequestDto);
}
