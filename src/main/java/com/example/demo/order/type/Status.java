package com.example.demo.order.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    OPEN("O"), COMPLETED("C");

    private final String code;
}