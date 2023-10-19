package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper( StoreMapper.class );
    StoreDto storeToStoreDto(Store store);
    Store storeDtoToStore(StoreDto storeDto);
    Store createStoreDtoToStore(CreateStoreDto createStoreDto);
    List<StoreDto> storeListToStoreDtoList(List<Store> storeList);
}
