package com.logistics.controller.order;

import com.logistics.dto.order.OrderCreationDTO;
import com.logistics.dto.order.OrderResponseDTO;
import com.logistics.dto.order.OrderUpdateDTO;
import com.logistics.model.Orders;
import com.logistics.service.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderCreationDTO orderDTO) {
        Orders orders = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(new OrderResponseDTO(orders));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> searchOrders(
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Pageable pageable) {

        Page<Orders> orders = orderService.searchOrders(
                orderId, customerName, status, startDate, endDate, pageable);

        List<OrderResponseDTO> dtos = orders.getContent().stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());

        Page<OrderResponseDTO> response = new PageImpl<>(
                dtos, pageable, orders.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        Orders orders = orderService.getOrderById(orderId);
        return ResponseEntity.ok(new OrderResponseDTO(orders));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderUpdateDTO updateDTO) {

        Orders updatedOrders = orderService.updateOrder(orderId, updateDTO);
        return ResponseEntity.ok(new OrderResponseDTO(updatedOrders));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}