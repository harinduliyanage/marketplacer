package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>,
        QuerydslPredicateExecutor<Cart> {
}
