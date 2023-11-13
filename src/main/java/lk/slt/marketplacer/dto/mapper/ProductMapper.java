package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.Product;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    CategoryMapper categoryMapper;

    public ProductDto productToProductDto(Product product) {
        ProductDto target = new ProductDto();
        //
        target.setId(product.getId());
        target.setName(product.getName());
        target.setDescription(product.getDescription());
        target.setSpecification(product.getSpecification());
        target.setProductStatus(product.getProductStatus());
        target.setBrand(product.getBrand());
        target.setPrice(product.getPrice());
        target.setCurrency(product.getCurrency());
        target.setUnits(product.getUnits());
        target.setDiscountAmount(product.getDiscountAmount());
        target.setDiscountType(product.getDiscountType());
        target.setImages(product.getImages());
        target.setVideos(product.getVideos());
        target.setReOrderLevel(product.getReOrderLevel());
        target.setCategory(mapCategory(product.getCategory()));

        return target;
    }

    @Mapping(target = "store", ignore = true)
    public abstract Product productDtoToProduct(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    public abstract Product createProductDtoToProduct(CreateProductDto createProductDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "name", source = "name",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", source = "description",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "brand", source = "brand",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "specification", source = "specification",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "price", source = "price",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "units", source = "units",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "reOrderLevel", source = "reOrderLevel",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productStatus", source = "productStatus",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "currency", source = "currency",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "discountType", source = "discountType",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "discountAmount", source = "discountAmount",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "videos", source = "videos",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "images", source = "images",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product updateProductDtoToProduct(UpdateProductDto updateProductDto, @MappingTarget Product entity);

    public abstract List<ProductDto> productListToProductDtoList(List<Product> productList);

    private CategoryDto mapCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        //
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setCategoryType(category.getCategoryType());
        if (null != category.getParentCategory()) {
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return categoryDto;
    }

    private CategoryDto mapParentCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        //
        if (category.getParentCategory() == null) {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setCategoryType(category.getCategoryType());
        } else {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setCategoryType(category.getCategoryType());
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return categoryDto;
    }
}
