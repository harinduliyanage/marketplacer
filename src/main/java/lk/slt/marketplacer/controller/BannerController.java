package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "banner-services")
@RequestMapping("/api/v1/banners")
public interface BannerController {
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public BannerDto createBanner(@RequestBody @Valid CreateBannerDto createBannerDto);

    @GetMapping(value = "/{bannerId}", produces = {"application/json"})
    public BannerDto getBanner(@PathVariable("bannerId") String bannerId);

    @GetMapping()
    public ListResponseDto<BannerDto> getBanners(@ParameterObject Pageable pageable);

    @PatchMapping(value = "/{bannerId}", consumes = {"application/json"}, produces = {"application/json"})
    public BannerDto updateBanner(@PathVariable("bannerId") String bannerId,
                                    @Valid @RequestBody UpdateBannerDto updateBannerDto);

    @DeleteMapping(value = "/{bannerId}", produces = {"application/json"})
    public BannerDto removeBanner(@PathVariable("bannerId") String bannerId);
}
