package com.yurim.item.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer stockQuantity;
}