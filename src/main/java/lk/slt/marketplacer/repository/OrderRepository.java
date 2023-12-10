package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends JpaRepository<Order, String>,
        QuerydslPredicateExecutor<Order> {
}