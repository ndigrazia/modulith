package com.example.demo.order.dto;

import org.jmolecules.event.types.DomainEvent;

import com.example.demo.eventaction.action.Action;
import com.example.demo.eventaction.action.CustomEventMarker;


@CustomEventMarker(eventAction = Action.PAYMENT)
public record OrderPaymentDto(String orderId, long amount) implements DomainEvent {
}
