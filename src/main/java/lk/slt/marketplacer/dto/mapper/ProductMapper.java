package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    CategoryMapper categoryMapper;

    //@Mapping(target = "categoryId", expression = "java(product.getCategory()==null? null : product.getCategory().getId())")
    public ProductDto productToProductDto(Product product) {
        System.out.println(product.getCategory().getName());
        ProductDto target = new ProductDto();


        target.setId(product.getId());
        target.setName(product.getName());
        target.setDescription(product.getDescription());
        target.setPublish(product.getPublish());
        target.setBrand(product.getBrand());
        target.setCurrency(product.getCurrency());
        if ( product.getUnits() != null ) {
            target.setUnits( String.valueOf( product.getUnits() ) );
        }
        target.setImages(product.getImages());
        target.setVideos(product.getVideos());
        target.setCategory(mapCategory(product.getCategory()));

        return  target;
    }

    @Mapping(target = "store", ignore = true)
    public abstract Product productDtoToProduct(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    public abstract Product createProductDtoToProduct(CreateProductDto createProductDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    public abstract Product updateProductDtoToProduct(UpdateProductDto updateProductDto);

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
        return  categoryDto;
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
