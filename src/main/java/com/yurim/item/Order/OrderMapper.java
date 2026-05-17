package com.yurim.item.Order;
import com.yurim.item.Product.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    // Product → Order Entity
    public Order toEntity(Product product, Integer quantity) {
        return new Order(product, quantity);
    }

    // Order Entity → Response DTO
    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProduct().getProductName(),
                order.getQuantity(),
                order.getOrderStatus()
        );
    }
}