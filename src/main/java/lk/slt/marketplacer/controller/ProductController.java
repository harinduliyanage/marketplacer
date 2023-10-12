package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lk.slt.marketplacer.dto.CreateProductDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.ProductDto;
import lk.slt.marketplacer.dto.UpdateProductDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "product-services")
@RequestMapping("/api/v1")
public interface ProductController {
    @PostMapping(value = "/users/{userId}/stores/{storeId}/products", consumes = {"application/json"}, produces = {"application/json"})
    public ProductDto createProduct(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @RequestBody CreateProductDto createProductDto);

    @GetMapping(value = "/users/{userId}/stores/{storeId}/products/{productId}", consumes = {"application/json"}, produces = {"application/json"})
    public ProductDto getProduct(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @PathVariable("productId") String productId);

    @GetMapping(value = "/users/{userId}/stores/{storeId}/products", consumes = {"application/json"}, produces = {"application/json"})
    public ListResponseDto<ProductDto> getStoreProducts(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/products", consumes = {"application/json"}, produces = {"application/json"})
    public ListResponseDto<ProductDto> getProducts(@ParameterObject Pageable pageable);

    @PatchMapping(value = "/users/{userId}/stores/{storeId}/products/{productId}", consumes = {"application/json"}, produces = {"application/json"})
    public ProductDto updateProduct(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @PathVariable("productId") String productId, @RequestBody UpdateProductDto updateProductDto);

    @DeleteMapping(value = "/users/{userId}/stores/{storeId}/products/{productId}", consumes = {"application/json"}, produces = {"application/json"})
    public ProductDto removeProduct(@PathVariable("userId") String userId, @PathVariable("storeId") String storeId, @PathVariable("productId") String productId);
}
