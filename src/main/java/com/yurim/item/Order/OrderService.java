package com.yurim.item.Order;
import com.yurim.item.Product.Product;
import com.yurim.item.Product.ProductRepository;
import com.yurim.item.Product.ProductResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    // 주문 생성
    @Transactional
    public OrderResponseDto create(OrderRequestDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("상품 없음"));
        int updatedCount = productRepository.decreaseStockAtomic(
                dto.getProductId(),
                dto.getQuantity()
        );
        if (updatedCount == 0) {
            throw new RuntimeException("재고 부족");
        }
        Order order = orderMapper.toEntity(product, dto.getQuantity());
        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

        // 단건 조회
    public OrderResponseDto get(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문 없음"));

        return orderMapper.toDto(order);
    }

    // 전체 조회
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAllWithProduct().stream()
                .map(orderMapper::toDto)
                .toList();
    }
}