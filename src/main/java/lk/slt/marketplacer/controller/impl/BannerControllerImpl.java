package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.BannerController;
import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.dto.mapper.BannerMapper;
import lk.slt.marketplacer.model.Banner;
import lk.slt.marketplacer.service.BannerService;
import lk.slt.marketplacer.util.BannerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerControllerImpl implements BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public BannerDto createBanner(CreateBannerDto createBannerDto) {
        Banner banner = bannerMapper.createBannerDtoToBanner(createBannerDto);
        Banner saved = bannerService.createBanner(banner);
        return bannerMapper.bannerToBannerDto(saved);
    }

    @Override
    public BannerDto getBanner(String bannerId) {
        Banner banner = bannerService.getBannerById(bannerId);
        return bannerMapper.bannerToBannerDto(banner);
    }

    @Override
    public ListResponseDto<BannerDto> getBanners(BannerType bannerType, Pageable pageable) {
        Page<Banner> page = bannerService.getBanners(bannerType, pageable);
        return ListResponseDto.<BannerDto>builder()
                .data(bannerMapper.bannerListToBannerDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public BannerDto updateBanner(String bannerId, UpdateBannerDto updateBannerDto) {
        Banner banner = bannerMapper.updateBannerDtoToBanner(updateBannerDto);
        Banner updatedBanner = bannerService.updateBanner(bannerId, banner);
        return bannerMapper.bannerToBannerDto(updatedBanner);
    }

    @Override
    public BannerDto removeBanner(String bannerId) {
        Banner banner = bannerService.removeBanner(bannerId);
        return bannerMapper.bannerToBannerDto(banner);
    }
}
