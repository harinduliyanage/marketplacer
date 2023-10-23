package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.StoreDto;
import lk.slt.marketplacer.dto.UpdateStoreDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "store-services")
@RequestMapping("/api/v1")
public interface StoreController {
    @PostMapping(value = "/users/{userId}/stores", consumes = {"application/json"}, produces = {"application/json"})
    public StoreDto createStore(@PathVariable("userId") String userId, @RequestBody CreateStoreDto createStoreDto);

    @GetMapping(value = "/users/{userId}/stores", produces = {"application/json"})
    public ListResponseDto<StoreDto> getUserStores(@PathVariable("userId") String userId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/users/{userId}/stores/{storeId}", produces = {"application/json"})
    public StoreDto getStore(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId);

    @PatchMapping(value = "/users/{userId}/stores/{storeId}", consumes = {"application/json"}, produces = {"application/json"})
    public StoreDto updateStore(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @RequestBody UpdateStoreDto updateStoreDto);

    @DeleteMapping(value = "/users/{userId}/stores/{storeId}", produces = {"application/json"})
    public StoreDto removeStore(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId);

    @GetMapping(value = "/stores", produces = {"application/json"})
    public ListResponseDto<StoreDto> getStores(@ParameterObject Pageable pageable);
}
