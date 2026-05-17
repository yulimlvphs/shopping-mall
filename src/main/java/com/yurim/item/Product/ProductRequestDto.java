package com.yurim.item.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductRequestDto {
    private String productName;
    private Integer productPrice;
    private Integer stockQuantity;
}