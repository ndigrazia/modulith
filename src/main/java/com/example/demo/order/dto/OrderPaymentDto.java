package com.example.demo.order.dto;

import org.jmolecules.event.types.DomainEvent;

public record OrderPaymentDto(String orderId, long amount) implements DomainEvent {
}
