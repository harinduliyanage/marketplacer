package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CartRepository extends JpaRepository<Cart, String>,
        QuerydslPredicateExecutor<Cart> {
}
