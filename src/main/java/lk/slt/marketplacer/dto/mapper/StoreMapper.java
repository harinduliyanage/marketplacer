package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
import lk.slt.marketplacer.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = SocialLinkMapper.class)
public interface StoreMapper {

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
    @Mapping(target = "storeStatus", ignore = true)
    Store updateStoreDtoToStore(UpdateStoreDto updateStoreDto);
    List<StoreDto> storeListToStoreDtoList(List<Store> storeList);
}
