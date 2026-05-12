package com.yurim.item.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
    SELECT o
    FROM Order o
    JOIN FETCH o.product """)
    List<Order> findAllWithProduct();

}