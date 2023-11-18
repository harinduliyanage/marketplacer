package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>,
        QuerydslPredicateExecutor<OrderDetails> {
}
