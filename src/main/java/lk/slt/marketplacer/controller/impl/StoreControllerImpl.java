package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.StoreController;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
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
    private StoreService storeService;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public StoreDto createStore(String userId, CreateStoreDto createStoreDto) {
        Store store = storeService.createStore(userId, storeMapper.createStoreDtoToStore(createStoreDto));
        return storeMapper.storeToStoreDto(store);
    }

    @Override
    public ListResponseDto<StoreDto> getUserStores(String userId, Pageable pageable) {
        Page<Store> page = storeService.getUserStores(userId, pageable);
        return ListResponseDto.<StoreDto>builder()
                .data(storeMapper.storeListToStoreDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public StoreDto getStore(String userId, String storeId) {
        return storeMapper.storeToStoreDto(storeService.getStore(userId, storeId));
    }

    @Override
    public StoreDto updateStore(String userId, String storeId, UpdateStoreDto updateStoreDto) {
        Store store = storeMapper.updateStoreDtoToStore(updateStoreDto);
        store.setId(storeId);
        //
        return storeMapper.storeToStoreDto(storeService.updateStore(userId,storeId,store));
    }

    @Override
    public StoreDto removeStore(String userId, String storeId) {
        return storeMapper.storeToStoreDto(storeService.removeStore(userId,storeId));
    }

    @Override
    public ListResponseDto<StoreDto> getStores(Pageable pageable) {
        Page<Store> page = storeService.getStores(pageable);
        return ListResponseDto.<StoreDto>builder()
                .data(storeMapper.storeListToStoreDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
