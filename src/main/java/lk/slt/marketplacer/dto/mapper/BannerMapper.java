package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.model.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    BannerDto bannerToBannerDto(Banner banner);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    Banner createBannerDtoToBanner(CreateBannerDto createBannerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "imageUrl", source = "imageUrl",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "link", source = "link",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "bannerType", source = "bannerType",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "index", source = "index",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "isFeatured", source = "isFeatured",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Banner updateBannerDtoToBanner(UpdateBannerDto updateBannerDto, @MappingTarget Banner banner);

    List<BannerDto> bannerListToBannerDtoList(List<Banner> banners);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    Banner updateBannerDtoToBanner(UpdateBannerDto updateBannerDto);
}
