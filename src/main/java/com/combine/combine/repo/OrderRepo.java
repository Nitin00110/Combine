package com.combine.combine.repo;

import com.combine.combine.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query
            ("SELECT COALESCE(SUM(o.pricePerUnit * o.quantity), 0) FROM Order o WHERE o.status = 'SHIPPED'")
    Double calculateTotalShippedRevenue();


}
