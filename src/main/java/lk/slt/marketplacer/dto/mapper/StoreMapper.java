package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
import lk.slt.marketplacer.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = SocialLinkMapper.class)
public interface StoreMapper {

    @Mapping(target = "categoryId", expression = "java(store.getCategory()==null? null : store.getCategory().getId())")
    StoreDto storeToStoreDto(Store store);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    Store storeDtoToStore(StoreDto storeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "storeStatus", ignore = true)
    Store createStoreDtoToStore(CreateStoreDto createStoreDto);

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
    Store updateStoreDtoToStore(UpdateStoreDto updateStoreDto, @MappingTarget Store store);

    List<StoreDto> storeListToStoreDtoList(List<Store> storeList);
}
