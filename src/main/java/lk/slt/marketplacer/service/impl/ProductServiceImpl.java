package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.CategoryTypeInvalidException;
import lk.slt.marketplacer.exceptions.ProductNotFoundException;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.Product;
import lk.slt.marketplacer.model.QProduct;
import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.repository.ProductRepository;
import lk.slt.marketplacer.service.CategoryService;
import lk.slt.marketplacer.service.ProductService;
import lk.slt.marketplacer.service.StoreService;
import lk.slt.marketplacer.util.CategoryType;
import lk.slt.marketplacer.util.Constants;
import lk.slt.marketplacer.util.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    CategoryService categoryService;

    @Override
    public Product createProduct(String userId, String storeId, String categoryId, Product product) {
        Store store = storeService.getStore(userId, storeId);
        Category category = categoryService.getCategoryById(categoryId);
        if (category.getCategoryType() != CategoryType.PRODUCT) {
            throw new CategoryTypeInvalidException(Constants.INVALID_CATEGORY_TYPE_MSG);
        }
        product.setStore(store);
        product.setProductStatus(ProductStatus.PENDING);
        product.setCategory(category);
        //
        Product savedProduct = productRepository.save(product);
        log.info("product has been successfully created {}", savedProduct);
        return savedProduct;
    }

    @Override
    public Product getProductById(String userId, String storeId, String productId) {
        Store store = storeService.getStore(userId, storeId);
        //
        QProduct qProduct = QProduct.product;
        BooleanExpression expression = qProduct.store.eq(store).and(qProduct.id.eq(productId));

        Optional<Product> found = productRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ProductNotFoundException(String.format(Constants.PRODUCT_NOT_FOUND_MSG, storeId, store));
        }
    }

    @Override
    public Page<Product> getStoreProducts(String userId, String storeId, Pageable pageable) {
        Store store = storeService.getStore(userId, storeId);
        QProduct qProduct = QProduct.product;
        BooleanExpression expression = qProduct.store.eq(store);
        return productRepository.findAll(expression, pageable);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(String userId, String storeId, String categoryId, String productId, Product product) {
        Product existingProduct = getProductById(userId, storeId, productId);
        storeService.getStore(userId, storeId);
        //
        if (product.getName() == null) {
            product.setName(existingProduct.getName());
        }
        if (product.getBrand() == null) {
            product.setBrand(existingProduct.getBrand());
        }
        if (product.getPrice() == null) {
            product.setPrice(existingProduct.getPrice());
        }
        if (product.getUnits() == null) {
            product.setUnits(existingProduct.getUnits());
        }
        if (product.getDescription() == null) {
            product.setDescription(existingProduct.getDescription());
        }
        if (product.getReOrderLevel() == null) {
            product.setReOrderLevel(existingProduct.getReOrderLevel());
        }
        if (product.getProductStatus() == null) {
            product.setProductStatus(existingProduct.getProductStatus());
        }
        if (product.getDiscountAmount() == null) {
            product.setDiscountAmount(existingProduct.getDiscountAmount());
        }
        if (product.getDiscountType() == null) {
            product.setDiscountType(existingProduct.getDiscountType());
        }
        if (product.getVideos() == null) {
            product.setVideos(existingProduct.getVideos());
        }
        if (product.getImages() == null) {
            product.setImages(existingProduct.getImages());
        }
        if (categoryId == null) {
            product.setCategory(existingProduct.getCategory());
        } else {
            Category category = categoryService.getCategoryById(categoryId);

            if (category.getCategoryType() != CategoryType.PRODUCT) {
                throw new CategoryTypeInvalidException(Constants.INVALID_CATEGORY_TYPE_MSG);
            }
            product.setCategory(category);
        }
        //
        Product updatedProduct = productRepository.save(product);
        log.info("product has been successfully updated {}", updatedProduct);
        return updatedProduct;
    }

    @Override
    public Product removeProduct(String userId, String storeId, String productId) {
        Product product = getProductById(userId, storeId, productId);
        productRepository.deleteById(productId);
        log.info("product has been successfully deleted {}", product);
        return product;
    }
}
