package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.CartItemsDto;
import lk.slt.marketplacer.dto.UpdateCartItemsDto;
import lk.slt.marketplacer.model.CartItems;
import lk.slt.marketplacer.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductService.class, ProductMapper.class})
public interface CartItemsMapper {
    CartItemsDto cartItemsToCartItemsDto(CartItems cartItems);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "productId", target = "product")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    CartItems updateCartItemsDtoToCartItems(UpdateCartItemsDto updateCartItemsDto);
}
