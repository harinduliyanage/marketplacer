package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.SocialLinkDto;
import lk.slt.marketplacer.model.SocialLink;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocialLinkMapper {
    SocialLinkMapper INSTANCE = Mappers.getMapper( SocialLinkMapper.class );
    SocialLinkDto socialLinkToSocialLinkDto(SocialLink socialLink);
    SocialLink socialLinkDtoToSocialLink(SocialLinkDto socialLinkDto);
}
