package com.example.demo.inventory;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ModulithException;
import com.example.demo.inventory.exposed.InventoryDto;
import com.example.demo.inventory.exposed.InventoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryDto fetchInventoryByName(String name) {
        return inventoryRepository.getInventoryByName(name)
                .map(InventoryUtil::mapInventoryDto)
                .orElseThrow(() -> new ModulithException("Could not find inventory by name: " + name));
    }

    @Override
    public List<InventoryDto> fetchAllInName(List<String> inventoryNames) {
        return inventoryRepository.getInventoryByNameIn(inventoryNames)
                .stream()
                .map(InventoryUtil::mapInventoryDto)
                .toList();
    }
}