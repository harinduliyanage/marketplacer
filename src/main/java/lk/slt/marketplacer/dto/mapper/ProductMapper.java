package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
import lk.slt.marketplacer.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    Product createProductDtoToProduct(CreateProductDto createProductDto);
    Product updateProductDtoToProduct(UpdateProductDto updateProductDto);
    List<ProductDto> productListToProductDtoList(List<Product> productList);
}
