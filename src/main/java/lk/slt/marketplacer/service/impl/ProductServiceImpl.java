package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.ProductNotFoundException;
import lk.slt.marketplacer.model.Product;
import lk.slt.marketplacer.model.QProduct;
import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.repository.ProductRepository;
import lk.slt.marketplacer.service.ProductService;
import lk.slt.marketplacer.service.StoreService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StoreService storeService;

    @Override
    public Product createProduct(String userId, String storeId, Product product) {
        Store store = storeService.getStore(userId, storeId);
        product.setStore(store);
        //
        Product savedProduct = productRepository.save(product);
        log.info("product has been successfully created {}", savedProduct);
        return savedProduct;
    }

    @Override
    public Product getProduct(String userId, String storeId, String productId) {
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
    public Product updateProduct(String userId, String storeId, String productId, Product product) {
        getProduct(userId,storeId,productId);

        Product updatedProduct = productRepository.save(product);
        log.info("product has been successfully updated {}", updatedProduct);
        return updatedProduct;
    }

    @Override
    public Product removeProduct(String userId, String storeId, String productId) {
        Product product = getProduct(userId,storeId,productId);
        productRepository.deleteById(productId);
        log.info("product has been successfully deleted {}", product);
        return product;
    }
}
