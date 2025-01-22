package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import com.example.demo.order.dto.InventoryRequestDto;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.dto.OrderPaymentDto;
import com.example.demo.order.dto.OrderResponseDto;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
public class OrderIntegrationTest {

     @Autowired
    OrderService orderService;

    @Test
    void createOrder() {
        List<InventoryRequestDto> requestDtoList =
                List.of(
                        new InventoryRequestDto("ruler", 3),
                        new InventoryRequestDto("pencil", 4)
                );

        OrderDto orderDto = new OrderDto("Test Man", "test@Test.com", requestDtoList);

        OrderResponseDto order = orderService.createOrder(orderDto);

        assertThat(order.message()).isEqualTo("Order Currently processed");
        assertThat(order.statusCode()).isEqualTo(102);
    }

    @Test
    void publishOrderPaymentDto(Scenario scenario) {

        scenario.publish(new OrderPaymentDto(UUID.randomUUID().toString(), 5000L))
                .andWaitForEventOfType(OrderPaymentDto.class)
                .matching(event -> event.amount() == 5000L)
                .toArriveAndVerify(ev -> System.out.println(ev.amount()));
    }

}
