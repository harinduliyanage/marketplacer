package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
import lk.slt.marketplacer.model.SocialLink;
import lk.slt.marketplacer.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SocialLinkMapper.class})
public abstract class StoreMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SocialLinkMapper socialLinkMapper;

    public StoreDto storeToStoreDto(Store store) {
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
        target.setCreatedAt(store.getCreatedAt());
        target.setLastUpdatedAt(store.getLastUpdatedAt());
        return target;
    }

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "storeStatus", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    public abstract Store createStoreDtoToStore(CreateStoreDto createStoreDto);


    public Store updateStoreDtoToStore(UpdateStoreDto updateStoreDto, @MappingTarget Store store) {
        if (updateStoreDto == null) {
            return store;
        }
        // copy store object
        Store mappedStore = new Store();
        mappedStore.setId(store.getId());
        mappedStore.setUser(store.getUser());
        mappedStore.setProducts(store.getProducts());
        mappedStore.setSocialLinks(store.getSocialLinks());
        mappedStore.setName(store.getName());
        mappedStore.setDescription(store.getDescription());
        mappedStore.setCategory(store.getCategory());
        mappedStore.setTelephone(store.getTelephone());
        mappedStore.setFax(store.getFax());
        mappedStore.setAddress(store.getAddress());
        mappedStore.setEmail(store.getEmail());
        mappedStore.setWebsite(store.getWebsite());
        mappedStore.setBrFilePath(store.getBrFilePath());
        mappedStore.setLogoFilePath(store.getLogoFilePath());
        mappedStore.setStoreStatus(store.getStoreStatus());
        mappedStore.setCreatedAt(store.getCreatedAt());
        //
        if (updateStoreDto.getName() != null) {
            mappedStore.setName(updateStoreDto.getName());
        }
        if (updateStoreDto.getDescription() != null) {
            mappedStore.setDescription(updateStoreDto.getDescription());
        }
        if (updateStoreDto.getTelephone() != null) {
            mappedStore.setTelephone(updateStoreDto.getTelephone());
        }
        if (updateStoreDto.getFax() != null) {
            mappedStore.setFax(updateStoreDto.getFax());
        }
        if (updateStoreDto.getAddress() != null) {
            mappedStore.setAddress(updateStoreDto.getAddress());
        }
        if (updateStoreDto.getEmail() != null) {
            mappedStore.setEmail(updateStoreDto.getEmail());
        }
        if (updateStoreDto.getWebsite() != null) {
            mappedStore.setWebsite(updateStoreDto.getWebsite());
        }
        if (updateStoreDto.getBrFilePath() != null) {
            mappedStore.setBrFilePath(updateStoreDto.getBrFilePath());
        }
        if (updateStoreDto.getLogoFilePath() != null) {
            mappedStore.setLogoFilePath(updateStoreDto.getLogoFilePath());
        }
        if (updateStoreDto.getStoreStatus() != null) {
            mappedStore.setStoreStatus(updateStoreDto.getStoreStatus());
        }
        //
        List<SocialLink> list = socialLinkMapper.socialLinkDtoListToSocialLinkList(updateStoreDto.getSocialLinks());
        if (list != null) {
            if (mappedStore.getSocialLinks() != null) {
                mappedStore.getSocialLinks().clear();
            }
            mappedStore.setSocialLinks(list);
        }
        //
        return mappedStore;
    }

    public abstract List<StoreDto> storeListToStoreDtoList(List<Store> storeList);
}
