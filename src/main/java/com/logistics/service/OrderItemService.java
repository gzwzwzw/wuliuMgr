package com.logistics.service;

import com.logistics.model.OrderItem;
import com.logistics.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem updateOrderItemQuantity(Long itemId, int quantity) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        item.setQuantity(quantity);
        return orderItemRepository.save(item);
    }

    public void deleteOrderItem(Long itemId) {
        orderItemRepository.deleteById(itemId);
    }
}