package lk.slt.marketplacer.service.impl;

import lk.slt.marketplacer.exceptions.WishlistNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.WishlistRepository;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.service.WishlistService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private UserService userService;

    /**
     * @param wishlist 
     * @return
     */
    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        log.info("wishlist has been successfully created {}", savedWishlist);
        return savedWishlist;
    }

    /**
     * @param userId 
     * @param wishlistId
     * @return
     */
    @Override
    public Wishlist getWishlist(String userId, String wishlistId) {
        User foundUser = userService.getUser(userId);
        Wishlist wishlist = foundUser.getWishlist();
        if (wishlist.getId().equals(wishlistId)) {
            return wishlist;
        } else {
            throw new WishlistNotFoundException(String.format(Constants.WISHLIST_NOT_FOUND_MSG, wishlistId, userId));
        }
    }

    /**
     * @param userId 
     * @return
     */
    @Override
    public List<Wishlist> getUserWishlists(String userId) {
        User foundUser = userService.getUser(userId);
        Wishlist wishlist = foundUser.getWishlist();
        List<Wishlist> wishlistList = new ArrayList<>();
        wishlistList.add(wishlist);
        return wishlistList;
    }

    /**
     * @param pageable 
     * @return
     */
    @Override
    public Page<Wishlist> getWishlists(Pageable pageable) {
        return this.wishlistRepository.findAll(pageable);
    }

    /**
     * @param userId 
     * @param wishlistId
     * @param wishlist
     * @return
     */
    @Override
    public Wishlist updateWishlist(String userId, String wishlistId, Wishlist wishlist) {
        getWishlist(userId, wishlistId);
        //
        wishlist.setId(wishlistId);
        Wishlist updatedWishlist = wishlistRepository.save(wishlist);
        log.info("wishlist has been successfully updated {}", updatedWishlist);
        return updatedWishlist;
    }

    /**
     * @param userId 
     * @param wishlistId
     * @return
     */
    @Override
    public Wishlist removeWishlist(String userId, String wishlistId) {
        Wishlist wishlist = getWishlist(userId, wishlistId);
        wishlistRepository.deleteById(wishlistId);
        log.info("wishlist has been successfully deleted {}", wishlist);
        return wishlist;
    }
}
