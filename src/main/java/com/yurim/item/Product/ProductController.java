package com.yurim.item.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 생성
    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        return productService.create(dto);
    }

    // 전체 조회
    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productService.get(id);
    }

    // 수정
    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto) {
        return productService.update(id, dto);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}