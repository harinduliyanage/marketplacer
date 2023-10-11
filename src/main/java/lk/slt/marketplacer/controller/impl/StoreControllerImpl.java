package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.StoreController;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.service.StoreService;
import lk.slt.marketplacer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreControllerImpl implements StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    @Override
    public StoreDto createStore(String userId, CreateStoreDto createStoreDto) {
        User user = userService.getUserById(userId);

        return null;
    }

    @Override
    public ListResponseDto<StoreDto> getStores(String userId, Pageable pageable) {
        Page<Store> page = this.storeService.getUserStores(userId, pageable);
        return ListResponseDto.<StoreDto>builder()
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                //.data(page.getContent())
                .totalPages(page.getTotalPages())
                .build();
    }
}
