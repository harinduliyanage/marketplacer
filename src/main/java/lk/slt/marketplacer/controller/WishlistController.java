package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.WishlistDto;
import lk.slt.marketplacer.dto.UpdateWishlistDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "wishlist-services")
@RequestMapping("/api/v1/users/{userId}/wishlists")
public interface WishlistController {
    @GetMapping(value = "/{wishlistId}", produces = {"application/json"})
    public WishlistDto getWishlist(@PathVariable("userId") String userId,
                           @PathVariable("wishlistId") String wishlistId);

    @GetMapping()
    public List<WishlistDto> getWishlists(@PathVariable("userId") String userId);

    @PatchMapping(value = "/{wishlistId}", consumes = {"application/json"}, produces = {"application/json"})
    public WishlistDto updateWishlist(@PathVariable("userId") String userId,
                              @PathVariable("wishlistId") String wishlistId,
                              @Valid @RequestBody UpdateWishlistDto updateWishlistDto);
}
