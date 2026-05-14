package com.yurim.item.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying // JPQL의 @Query는 기본적으로 '조회'용인데 지금은 '수정'용이기 때문에 그 사실을 알려주는 용도
    @Query("""
        UPDATE Product p
        SET p.stockQuantity = p.stockQuantity - :quantity /* =을 기준으로 오른쪽에 있는 값을 왼쪽에 넣어주는 것 */
        WHERE p.id = :productId
          AND p.stockQuantity >= :quantity /*조건을 검사한 후에 변경사항을 저장하기 때문에 원자적 차감 실현*/
    """)
    int decreaseStockAtomic(Long productId, Integer quantity);
}