package com.example.demo.payment.typeconverter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import com.example.demo.exception.ModulithException;
import com.example.demo.payment.type.PaymentStatus;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        if(status == null) throw new ModulithException("Status can not be null");
        return status.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String code) {
        if(code == null) throw new IllegalArgumentException("Code can not be null");
        return Arrays.stream(PaymentStatus.values())
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(ModulithException::new);
    }
}
