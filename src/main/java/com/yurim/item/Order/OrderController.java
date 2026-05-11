package com.yurim.item.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto dto) {
        return orderService.create(dto);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public OrderResponseDto get(@PathVariable Long id) {
        return orderService.get(id);
    }
}