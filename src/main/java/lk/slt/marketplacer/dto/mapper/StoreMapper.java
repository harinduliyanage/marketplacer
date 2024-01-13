package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
import lk.slt.marketplacer.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SocialLinkMapper.class})
public abstract class StoreMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SocialLinkMapper socialLinkMapper;

    public StoreDto storeToStoreDto(Store store){
        StoreDto target = new StoreDto();
        //
        target.setId(store.getId());
        target.setName(store.getName());
        target.setAddress(store.getAddress());
        target.setCategory(categoryMapper.mapCategoryWithParents(store.getCategory()));
        target.setDescription(store.getDescription());
        target.setWebsite(store.getWebsite());
        target.setStoreStatus(store.getStoreStatus());
        target.setSocialLinks(socialLinkMapper.socialLinkListToSocialLinkDtoList(store.getSocialLinks()));
        target.setFax(store.getFax());
        target.setEmail(store.getEmail());
        target.setTelephone(store.getTelephone());
        target.setBrFilePath(store.getBrFilePath());
        target.setLogoFilePath(store.getLogoFilePath());
        return  target;
    }

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "storeStatus", ignore = true)
    @Mapping(target = "category", ignore = true)
    public abstract  Store createStoreDtoToStore(CreateStoreDto createStoreDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "name", source = "name",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", source = "description",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "telephone", source = "telephone",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "fax", source = "fax",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "address", source = "address",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "email",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "website", source = "website",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "brFilePath", source = "brFilePath",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "logoFilePath", source = "logoFilePath",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "storeStatus", source = "storeStatus",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "socialLinks", source = "socialLinks",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  Store updateStoreDtoToStore(UpdateStoreDto updateStoreDto, @MappingTarget Store store);

    public abstract  List<StoreDto> storeListToStoreDtoList(List<Store> storeList);
}
