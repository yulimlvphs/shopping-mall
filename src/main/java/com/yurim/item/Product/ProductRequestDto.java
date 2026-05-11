package com.yurim.item.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

    private String productName;
    private Integer productPrice;

}