package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CartItemsMapper.class})
public interface CartMapper {
    CartDto cartToCartDto(Cart cart);

    List<CartDto> cartListToCartDtoList(List<Cart> carts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    Cart updateCartDtoToCart(UpdateCartDto updateCartDto);
}
