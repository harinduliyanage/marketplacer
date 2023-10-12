package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public Product createProduct(String userId,String storeId,Product product);
    public Product getProduct(String userId, String storeId, String productId);
    public Page<Product> getStoreProducts(String userId, String storeId, Pageable pageable);
    public Page<Product> getProducts(Pageable pageable);
}
