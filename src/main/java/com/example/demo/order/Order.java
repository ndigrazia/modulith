package com.example.demo.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

import com.example.demo.order.type.Status;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderIdentifier;
    private String customerName;
    private String customerEmail;
    private Timestamp orderDate = Timestamp.from(Instant.now());
    private Status status = Status.OPEN;
}