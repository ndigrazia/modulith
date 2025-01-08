package com.example.demo.inventory;

import com.example.demo.inventory.exposed.InventoryDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryUtil {

    public InventoryDto mapInventoryDto(Inventory inventory) {
        return new InventoryDto(inventory.getId(), inventory.getName(), inventory.getDescription(), inventory.getPrice());
    }
}
