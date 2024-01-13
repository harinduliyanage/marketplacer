package lk.slt.marketplacer.repository;

import lk.slt.marketplacer.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository  extends JpaRepository<Banner, String>,
        QuerydslPredicateExecutor<Banner> {
}
