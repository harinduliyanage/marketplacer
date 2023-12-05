package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String>,
        QuerydslPredicateExecutor<Address> {
}
