package lk.slt.marketplacer.service.impl;

import lk.slt.marketplacer.exceptions.CartNotFoundException;
import lk.slt.marketplacer.model.Cart;
import lk.slt.marketplacer.model.CartItems;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.repository.CartRepository;
import lk.slt.marketplacer.service.CartService;
import lk.slt.marketplacer.service.UserService;
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
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;

    @Override
    public Cart createCart(Cart cart) {
        cart.setCartItems(new ArrayList<CartItems>());
        Cart savedCart = cartRepository.save(cart);
        log.info("cart has been successfully created {}", savedCart);
        return savedCart;
    }

    @Override
    public Cart getCart(String userId, String cartId) {
        User foundUser = userService.getUser(userId);
        Cart cart = foundUser.getCart();
        if (cart.getId().equals(cartId)) {
            return cart;
        } else {
            throw new CartNotFoundException(String.format(Constants.CART_NOT_FOUND_MSG, cartId, userId));
        }
    }

    @Override
    public List<Cart> getUserCarts(String userId) {
        User foundUser = userService.getUser(userId);
        Cart cart = foundUser.getCart();
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        return cartList;
    }

    @Override
    public Page<Cart> getCarts(Pageable pageable) {
        return this.cartRepository.findAll(pageable);
    }

    @Override
    public Cart updateCart(String userId, String cartId, Cart cart) {
        getCart(userId, cartId);
        //
        cart.setId(cartId);
        Cart updatedCart = cartRepository.save(cart);
        log.info("cart has been successfully updated {}", updatedCart);
        return updatedCart;
    }

    @Override
    public Cart removeCart(String userId, String cartId) {
        Cart cart = getCart(userId, cartId);
        cartRepository.deleteById(cartId);
        log.info("Cart has been successfully deleted {}", cart);
        return cart;
    }
}
