package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author harindu.sul@gmail.com
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, String> ,
        QuerydslPredicateExecutor<Store> {
}
