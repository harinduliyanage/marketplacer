package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.StoreController;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.mapper.StoreMapper;
import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreControllerImpl implements StoreController {
    @Autowired
    StoreService storeService;
    @Override
    public StoreDto createStore(String userId, CreateStoreDto createStoreDto) {
        Store store = storeService.createStore(userId,StoreMapper.INSTANCE.createStoreDtoToStore(createStoreDto));
        return StoreMapper.INSTANCE.storeToStoreDto(store);
    }

    @Override
    public ListResponseDto<StoreDto> getStores(String userId, Pageable pageable) {
        Page<Store> page = storeService.getStores(pageable);
        return ListResponseDto.<StoreDto>builder()
                .data(StoreMapper.INSTANCE.storeListToStoreDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
