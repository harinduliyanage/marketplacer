package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.model.Product;
import lk.slt.marketplacer.model.Wishlist;
import lk.slt.marketplacer.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductService.class, ProductMapper.class})
public interface WishlistMapper {
    WishlistDto wishlistToWishlistDto(Wishlist wishlist);

    List<WishlistDto> wishlistListToWishlistDtoList(List<Wishlist> wishlists);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(source = "productIds", target = "products")
    Wishlist updateWishlistDtoToWishlist(UpdateWishlistDto updateWishlistDto);
}
