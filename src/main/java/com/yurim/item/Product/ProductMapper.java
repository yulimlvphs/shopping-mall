package com.yurim.item.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    // DTO → Entity
    public Product toEntity(ProductRequestDto dto) {
        return new Product(
                dto.getProductName(),
                dto.getProductPrice()
        );
    }

    // Entity → DTO
    public ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getProductPrice()
        );
    }
}