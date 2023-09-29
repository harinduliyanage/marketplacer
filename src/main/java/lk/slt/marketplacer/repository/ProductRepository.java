package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author harindu.sul@gmail.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String>,
        QuerydslPredicateExecutor<Product> {
}
