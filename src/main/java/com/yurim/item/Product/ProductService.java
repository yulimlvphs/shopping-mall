package com.yurim.item.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // 생성
    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);

        return productMapper.toDto(saved);
    }

    // 전체 조회
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    // 단건 조회
    public ProductResponseDto get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        return productMapper.toDto(product);
    }

    // 수정
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품 없음"));
        product.update(
                dto.getProductName(),
                dto.getProductPrice(),
                dto.getStockQuantity()
        );
        return productMapper.toDto(product);
    }

    // 삭제
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}