package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.CartNotFoundException;
import lk.slt.marketplacer.model.Cart;
import lk.slt.marketplacer.model.QCart;
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

import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;

    @Override
    public Cart createCart(String userId, Cart cart) {
        User foundUser = userService.getUser(userId);
        cart.setUser(foundUser);
        Cart savedCart = cartRepository.save(cart);
        log.info("cart has been successfully created {}", savedCart);
        return savedCart;
    }

    @Override
    public Cart getCart(String userId, String cartId) {
        User foundUser = userService.getUser(userId);
        QCart qCart = QCart.cart;
        BooleanExpression expression = qCart.user.eq(foundUser).and(qCart.id.eq(cartId));
        Optional<Cart> found = cartRepository.findOne(expression);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new CartNotFoundException(String.format(Constants.CART_NOT_FOUND_MSG, cartId, userId));
        }
    }

    @Override
    public Cart getUserCart(String userId) {
        User foundUser = userService.getUser(userId);
        QCart qCart = QCart.cart;
        BooleanExpression expression = qCart.user.eq(foundUser);
        Optional<Cart> found = cartRepository.findOne(expression);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new CartNotFoundException(String.format(Constants.USER_CART_NOT_FOUND_MSG, userId));
        }
    }

    @Override
    public Page<Cart> getCarts(Pageable pageable) {
        return this.cartRepository.findAll(pageable);
    }

    @Override
    public Cart updateCart(String userId, String cartId, Cart cart) {
        Cart foundCart = getCart(userId, cartId);
        //
        cart.setId(cartId);
        cart.setUser(foundCart.getUser());
        Cart updatedCart = cartRepository.save(cart);
        log.info("cart has been successfully updated {}", updatedCart);
        return updatedCart;
    }

    @Override
    public Cart removeCart(String userId, String cartId) {
        Cart cart = getCart(userId, cartId);
        cartRepository.deleteById(userId);
        log.info("Cart has been successfully deleted {}", cart);
        return cart;
    }
}
