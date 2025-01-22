package com.example.demo.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import com.example.demo.order.dto.OrderPaymentDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    @ApplicationModuleListener
    void on(final OrderPaymentDto paymentDto) {
        log.info("Order payment received in listener: {}", paymentDto);

        Payment payment = new Payment();
        
        payment.setOrderId(paymentDto.orderId());
        payment.setAmount(paymentDto.amount());

        paymentRepository.save(payment);
    }

}
