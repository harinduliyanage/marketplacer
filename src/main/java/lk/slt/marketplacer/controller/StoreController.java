package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.CreateStoreDto;
import lk.slt.marketplacer.dto.StoreDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "store-services")
public interface StoreController {
    @PostMapping(value = "/users/{userId}/stores", consumes = {"application/json"}, produces = {"application/json"})
    public StoreDto createStore(@PathVariable("userId") String userId, @RequestBody CreateStoreDto createStoreDto);

    @GetMapping(value = "/users/{userId}/stores", consumes = {"application/json"}, produces = {"application/json"})
    public StoreDto getStores(@PathVariable("userId") String userId);
}
