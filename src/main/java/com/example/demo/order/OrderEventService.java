package com.example.demo.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.order.dto.EmailDto;
import com.example.demo.order.dto.OrderPaymentDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void completeOrder(final OrderPaymentDto orderPaymentDto, EmailDto emailDto) {

        log.info("Completing order payment with details{}", orderPaymentDto);
        eventPublisher.publishEvent(orderPaymentDto);

       //log.info("Sending email for order {}", emailDto);
       //eventPublisher.publishEvent(emailDto);
    }
}
