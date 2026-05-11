package com.yurim.item.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 생성
    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = new Product(dto.getProductName(), dto.getProductPrice());
        Product saved = productRepository.save(product);

        return new ProductResponseDto(
                saved.getId(),
                saved.getProductName(),
                saved.getProductPrice()
        );
    }

    // 전체 조회
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponseDto(
                        p.getId(),
                        p.getProductName(),
                        p.getProductPrice()))
                .toList();
    }

    // 단건 조회
    public ProductResponseDto get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        return new ProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getProductPrice()
        );
    }

    // 수정
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        product.update(dto.getProductName(), dto.getProductPrice());

        return new ProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getProductPrice()
        );
    }

    // 삭제
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}