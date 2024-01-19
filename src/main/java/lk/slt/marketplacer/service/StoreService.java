package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.util.StoreStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {

    public Store createStore(String userId, String categoryId, Store store);

    public Store getStore(String userId, String storeId);

    public Store updateStore(String userId, String categoryId, String storeId, Store store);

    public Store removeStore(String userId, String storeId);

    public Page<Store> getUserStores(String userId, Pageable pageable);

    public Page<Store> getStores(StoreStatus storeStatus, Pageable pageable);

    public Store getStoreById(String storeId);
}
