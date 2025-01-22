package com.example.demo.order;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.inventory.exposed.InventoryDto;
import com.example.demo.inventory.exposed.InventoryService;
import com.example.demo.order.dto.EmailDto;
import com.example.demo.order.dto.InventoryRequestDto;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.dto.OrderPaymentDto;
import com.example.demo.order.dto.OrderResponseDto;
import com.example.demo.order.type.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;
    
    private final OrderRepository orderRepository;

    private final OrderInventoryRepository orderInventoryRepository;
    
    private final OrderEventService orderEventService;

    public OrderResponseDto createOrder(OrderDto orderDto) {

        // get Inventories by names
        List<String> inventoryNames = orderDto.inventoryRequestDtoList().stream()
                .map(InventoryRequestDto::inventoryName)
                .toList();

        // get Inventories
        List<InventoryDto> inventories = inventoryService.fetchAllInName(inventoryNames);

        final AtomicLong amount = new AtomicLong();
        // persist the order
        Order order = builAndPersistOrder(orderDto);
        log.info("Order created: {}", order);

        // build and persist the OrderInventory
        buildAndPersistOrderInventories(orderDto, inventories, order.getId(),amount);

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto(order.getOrderIdentifier(), amount.get());

        EmailDto emailDto = new EmailDto(orderDto.customerEmail(), orderDto.customerName(), order.getOrderIdentifier(),
                orderPaymentDto.amount(), false);

        orderEventService.completeOrder(orderPaymentDto, emailDto);

        return new OrderResponseDto("Order Currently processed", 102);
    }

    private void buildAndPersistOrderInventories(OrderDto orderDto, List<InventoryDto> inventories, long orderId, AtomicLong amount) {
        List<OrderInventory> orderInventories = new ArrayList<>(inventories.size());

        inventories.forEach(inventoryDto -> {
            InventoryRequestDto inventoryRequestDto = getInventoryRequestDtoByName(inventoryDto.name(), orderDto.inventoryRequestDtoList());
            
            OrderInventory orderInventory = new OrderInventory();

            orderInventory.setOrderId(orderId);
            orderInventory.setInventoryId(inventoryDto.id());
            orderInventory.setQty(inventoryRequestDto.qty());

            long totalPrice = inventoryDto.price() * inventoryRequestDto.qty();
            orderInventory.setTotalQtyPrice(totalPrice);

            orderInventories.add(orderInventory);
            amount.addAndGet(totalPrice);
        });

        log.info("Order Inventories persisted: {}", orderInventories);
        orderInventoryRepository.saveAll(orderInventories);

    }

    private static InventoryRequestDto getInventoryRequestDtoByName(String inventoryName, List<InventoryRequestDto> inventoryRequestDtoList) {

        return inventoryRequestDtoList
                .stream()
                .filter(inv -> inv.inventoryName().equals(inventoryName))
                .findFirst().orElse(null);
    }

    private Order builAndPersistOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderIdentifier(UUID.randomUUID().toString());
        order.setCustomerName(orderDto.customerName());
        order.setCustomerEmail(orderDto.customerEmail());
        order.setStatus(Status.COMPLETED);

        return orderRepository.save(order);
    }
}
