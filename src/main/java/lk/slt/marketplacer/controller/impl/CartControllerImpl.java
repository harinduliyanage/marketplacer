package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.CartController;
import lk.slt.marketplacer.dto.CartDto;
import lk.slt.marketplacer.dto.UpdateCartDto;
import lk.slt.marketplacer.dto.mapper.CartMapper;
import lk.slt.marketplacer.model.Cart;
import lk.slt.marketplacer.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartControllerImpl implements CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;

    @Override
    public CartDto getCart(String userId, String cartId) {
        Cart createdCart = cartService.getCart(userId, cartId);
        return cartMapper.cartToCartDto(createdCart);
    }

    @Override
    public List<CartDto> getCarts(String userId) {
        List<Cart> cart = cartService.getUserCarts(userId);
        return cartMapper.cartListToCartDtoList(cart);
    }

    @Override
    public CartDto updateCart(String userId, String cartId, UpdateCartDto updateCartDto) {
        Cart cart = cartMapper.updateCartDtoToCart(updateCartDto);
        Cart updatedCart = cartService.updateCart(userId,cartId,cart);
        return cartMapper.cartToCartDto(updatedCart);
    }
}
