package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author harindu.sul@gmail.com
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, String>,
        QuerydslPredicateExecutor<Review> {
}
