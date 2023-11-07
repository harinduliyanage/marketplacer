package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.SocialLinkDto;
import lk.slt.marketplacer.model.SocialLink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocialLinkMapper {

    SocialLinkDto socialLinkToSocialLinkDto(SocialLink socialLink);
    @Mapping(target = "id", ignore = true)
    SocialLink socialLinkDtoToSocialLink(SocialLinkDto socialLinkDto);
}
