package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.StoreController;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.StoreDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreControllerImpl implements StoreController {

    @Override
    public StoreDto createStore(String userId, CreateStoreDto createStoreDto) {
        return null;
    }

    @Override
    public ListResponseDto<StoreDto> getStores(String userId, Pageable pageable) {
        return null;
    }
}
