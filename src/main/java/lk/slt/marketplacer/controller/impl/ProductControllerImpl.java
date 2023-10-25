package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.ProductController;
import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
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
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto createProduct(String userId, String storeId, CreateProductDto createProductDto) {
        Product createdProduct = productService.createProduct(userId, storeId, productMapper.createProductDtoToProduct(createProductDto));
        return productMapper.productToProductDto(createdProduct);
    }

    @Override
    public ProductDto getStoreProduct(String userId, String storeId, String productId) {
        return productMapper.productToProductDto(productService.getProduct(userId, storeId, productId));
    }

    @Override
    public ListResponseDto<ProductDto> getStoreProducts(String userId, String storeId, Pageable pageable) {
        Page<Product> page = productService.getStoreProducts(userId, storeId, pageable);
        return ListResponseDto.<ProductDto>builder()
                .data(productMapper.productListToProductDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public ListResponseDto<ProductDto> getProducts(Pageable pageable) {
        Page<Product> page = productService.getProducts(pageable);
        return ListResponseDto.<ProductDto>builder()
                .data(productMapper.productListToProductDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public ProductDto updateProduct(String userId, String storeId, String productId, UpdateProductDto updateProductDto) {
        Product product = productMapper.updateProductDtoToProduct(updateProductDto);
        product.setId(productId);
        //
        return productMapper.productToProductDto(productService.updateProduct(userId, storeId, productId, product));
    }

    @Override
    public ProductDto removeProduct(String userId, String storeId, String productId) {
        return productMapper.productToProductDto(productService.removeProduct(userId, storeId, productId));
    }
}
