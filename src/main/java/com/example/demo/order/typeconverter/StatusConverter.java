package com.example.demo.order.typeconverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import com.example.demo.exception.ModulithException;
import com.example.demo.order.type.Status;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(status == null) throw new ModulithException("Status can not be null");
        return status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(String code) {
        return Arrays.stream(Status.values())
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new ModulithException("No status found"));
    }
}