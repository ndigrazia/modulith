package com.example.demo.order.dto;

import org.jmolecules.event.types.DomainEvent;

public record EmailDto(String email, String customerName, String orderIdentifier, long totalAmount, boolean orderComplete) implements DomainEvent { }