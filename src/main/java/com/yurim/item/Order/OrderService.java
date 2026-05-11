package com.yurim.item.Order;
import com.yurim.item.Product.Product;
import com.yurim.item.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    public OrderResponseDto create(OrderRequestDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        Order order = new Order(product);
        Order saved = orderRepository.save(order);

        return new OrderResponseDto(
                saved.getId(),
                saved.getProduct().getProductName()
        );
    }

    // 단건 조회
    public OrderResponseDto get(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문 없음"));

        return new OrderResponseDto(
                order.getId(),
                order.getProduct().getProductName()
        );
    }
}