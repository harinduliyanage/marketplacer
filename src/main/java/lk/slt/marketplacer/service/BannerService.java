package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Banner;
import lk.slt.marketplacer.util.BannerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BannerService {
    public Banner createBanner(Banner banner);

    public Banner getBannerById(String id);

    public Page<Banner> getBanners(BannerType bannerType, Pageable pageable);

    public Banner updateBanner(String id, Banner banner);

    public Banner removeBanner(String id);
}
