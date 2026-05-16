package com.yurim.item.Product;
import com.yurim.item.global.exception.ProductNotFoundException;
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
                .orElseThrow(ProductNotFoundException::new);

        return productMapper.toDto(product);
    }

    // 수정
    public ProductResponseDto update(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.update(
                dto.getProductName(),
                dto.getProductPrice(),
                dto.getStockQuantity()
        );

        return productMapper.toDto(product);
    }

    // 삭제
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new); // 상품이 존재하지 않으면 해당 사실을 알려주기 위해 존재

        productRepository.delete(product);
    }
}