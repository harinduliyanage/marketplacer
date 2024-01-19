package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.SocialLinkDto;
import lk.slt.marketplacer.model.SocialLink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocialLinkMapper {

    SocialLinkDto socialLinkToSocialLinkDto(SocialLink socialLink);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    SocialLink socialLinkDtoToSocialLink(SocialLinkDto socialLinkDto);
    List<SocialLinkDto> socialLinkListToSocialLinkDtoList(List<SocialLink> socialLink);
    List<SocialLink> socialLinkDtoListToSocialLinkList(List<SocialLinkDto> socialLink);
}
