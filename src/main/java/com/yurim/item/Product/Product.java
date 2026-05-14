package com.yurim.item.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Integer productPrice;

    private Integer stockQuantity; // 상품재고

    public Product(String productName, Integer productPrice, Integer stockQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
    }

    public void update(String productName, Integer productPrice, Integer stockQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
    }
}