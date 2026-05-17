package com.yurim.item.Order;
import com.yurim.item.Product.Product;
import com.yurim.item.Product.ProductRepository;
import com.yurim.item.global.exception.InsufficientStockException;
import com.yurim.item.global.exception.OrderNotFoundException;
import com.yurim.item.global.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
                .orElseThrow(ProductNotFoundException::new);
        int updatedCount = productRepository.decreaseStockAtomic(
                dto.getProductId(),
                dto.getQuantity()
        );
        if (updatedCount == 0) {
            throw new InsufficientStockException();
        }
        Order order = orderMapper.toEntity(product, dto.getQuantity());
        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

        // 단건 조회
    public OrderResponseDto get(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        return orderMapper.toDto(order);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAllWithProduct().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    public OrderResponseDto cancel(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        // 이미 취소된 주문 방지
        if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("이미 취소된 주문");
        }

        // 주문 상태 변경
        order.cancel();

        // 재고 복구
        productRepository.restoreStockAtomic(
                order.getProduct().getId(),
                order.getQuantity()
        );

        return orderMapper.toDto(order);
    }
}