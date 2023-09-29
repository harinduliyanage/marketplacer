package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.StoreController;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreControllerImpl implements StoreController {

    @Override
    public StoreDto createStore(String userId, CreateStoreDto createStoreDto) {
        return null;
    }

    @Override
    public StoreDto getStores(String userId) {
        return null;
    }
}
