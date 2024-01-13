package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WishlistService {
    public Wishlist createWishlist(Wishlist wishlist);

    public Wishlist getWishlist(String userId, String wishlistId);

    public List<Wishlist> getUserWishlists(String userId);

    public Page<Wishlist> getWishlists(Pageable pageable);

    public Wishlist updateWishlist(String userId, String wishlistId, Wishlist wishlist);

    public Wishlist removeWishlist(String userId, String wishlistId);
}
