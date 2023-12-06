package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.CartDto;
import lk.slt.marketplacer.dto.UpdateCartDto;
import org.springframework.web.bind.annotation.*;

@Tag(name = "cart-services")
@RequestMapping("/api/v1/users/{userId}/cart")
public interface CartController {
    @GetMapping(value = "/{cartId}", produces = {"application/json"})
    public CartDto getCart(@PathVariable("userId") String userId,
                           @PathVariable("cartId") String cartId);

    @GetMapping()
    public CartDto getCarts(@PathVariable("userId") String userId);

    @PatchMapping(value = "/{cartId}", consumes = {"application/json"}, produces = {"application/json"})
    public CartDto updateCart(@PathVariable("userId") String userId,
                              @PathVariable("cartId") String cartId,
                              @Valid @RequestBody UpdateCartDto updateCartDto);
}
