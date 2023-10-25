package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
import lk.slt.marketplacer.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
    ProductDto productToProductDto(Product product);
    @Mapping(target = "store", ignore = true)
    Product productDtoToProduct(ProductDto productDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    Product createProductDtoToProduct(CreateProductDto createProductDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    Product updateProductDtoToProduct(UpdateProductDto updateProductDto);
    List<ProductDto> productListToProductDtoList(List<Product> productList);
}
