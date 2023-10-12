package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.ProductController;
import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.mapper.ProductMapper;
import lk.slt.marketplacer.model.Product;
import lk.slt.marketplacer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerImpl implements ProductController {
    @Autowired
    ProductService productService;

    @Override
    public ProductDto createProduct(String userId, String storeId, CreateProductDto createProductDto) {
        Product createdProduct = productService.createProduct(userId, storeId, ProductMapper.INSTANCE.createProductDtoToProduct(createProductDto));
        return ProductMapper.INSTANCE.productToProductDto(createdProduct);
    }

    @Override
    public ProductDto getProduct(String userId, String storeId, String productId, Pageable pageable) {
        return ProductMapper.INSTANCE.productToProductDto(productService.getProduct(userId, storeId, productId));
    }

    @Override
    public ListResponseDto<ProductDto> getProducts(String userId, String storeId, Pageable pageable) {
        Page<Product> page = productService.getStoreProducts(userId, storeId, pageable);
        return ListResponseDto.<ProductDto>builder()
                .data(ProductMapper.INSTANCE.productListToProductDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
