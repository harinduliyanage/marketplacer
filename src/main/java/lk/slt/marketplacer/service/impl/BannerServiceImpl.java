package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.BannerNotFoundException;
import lk.slt.marketplacer.model.Banner;
import lk.slt.marketplacer.model.QBanner;
import lk.slt.marketplacer.repository.BannerRepository;
import lk.slt.marketplacer.service.BannerService;
import lk.slt.marketplacer.util.BannerType;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner createBanner(Banner banner) {
        Banner saveBanner = bannerRepository.save(banner);
        log.info("banner has been successfully saved {}", saveBanner);
        return saveBanner;
    }

    @Override
    public Banner getBannerById(String id) {
        QBanner qBanner = QBanner.banner;
        BooleanExpression expression = qBanner.id.eq(id);
        Optional<Banner> isFound = bannerRepository.findOne(expression);
        if (isFound.isPresent()) {
            return isFound.get();
        } else {
            throw new BannerNotFoundException(String.format(Constants.BANNER_NOT_FOUND_MSG, id));
        }
    }

    @Override
    public Page<Banner> getBanners(BannerType bannerType, Pageable pageable) {
        if (bannerType != null) {
            QBanner qBanner = QBanner.banner;
            BooleanExpression expression = qBanner.bannerType.eq(bannerType);
            return bannerRepository.findAll(expression, pageable);
        }
        return bannerRepository.findAll(pageable);
    }

    @Override
    public Banner updateBanner(String id, Banner banner) {
        Banner found = getBannerById(id);
        banner.setId(found.getId());
        Banner save = bannerRepository.save(banner);
        log.info("banner updated successfully {}", save);
        return save;
    }

    @Override
    public Banner removeBanner(String id) {
        Banner banner = getBannerById(id);
        //
        bannerRepository.delete(banner);
        log.info("banner has been deleted successfully {}", banner);
        return banner;
    }
}
