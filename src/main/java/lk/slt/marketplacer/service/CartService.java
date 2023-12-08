package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    public Cart createCart(Cart cart);

    public Cart getCart(String userId, String cartId);

    public List<Cart> getUserCarts(String userId);

    public Page<Cart> getCarts(Pageable pageable);

    public Cart updateCart(String userId, String cartId, Cart cart);

    public Cart removeCart(String userId, String cartId);
}
