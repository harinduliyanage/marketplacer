package lk.slt.marketplacer.repository;


import lk.slt.marketplacer.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>,
        QuerydslPredicateExecutor<Category> {
}
