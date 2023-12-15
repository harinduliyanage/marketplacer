package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.WishlistController;
import lk.slt.marketplacer.dto.WishlistDto;
import lk.slt.marketplacer.dto.UpdateWishlistDto;
import lk.slt.marketplacer.dto.mapper.WishlistMapper;
import lk.slt.marketplacer.model.Wishlist;
import lk.slt.marketplacer.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishlistControllerImpl implements WishlistController {
    @Autowired
    WishlistService wishlistService;
    @Autowired
    WishlistMapper wishlistMapper;

    @Override
    public WishlistDto getWishlist(String userId, String wishlistId) {
        Wishlist createdWishlist = wishlistService.getWishlist(userId, wishlistId);
        return wishlistMapper.wishlistToWishlistDto(createdWishlist);
    }

    @Override
    public List<WishlistDto> getWishlists(String userId) {
        List<Wishlist> wishlist = wishlistService.getUserWishlists(userId);
        return wishlistMapper.wishlistListToWishlistDtoList(wishlist);
    }

    @Override
    public WishlistDto updateWishlist(String userId, String wishlistId, UpdateWishlistDto updateWishlistDto) {
        Wishlist wishlist = wishlistMapper.updateWishlistDtoToWishlist(updateWishlistDto);
        Wishlist updatedWishlist = wishlistService.updateWishlist(userId,wishlistId,wishlist);
        return wishlistMapper.wishlistToWishlistDto(updatedWishlist);
    }
}
