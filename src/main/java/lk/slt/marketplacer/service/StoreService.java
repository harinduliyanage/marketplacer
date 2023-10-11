package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {

    public Store createStore(String userId, Store store);
    public Store getStore(String userId, String storeId);
    public Page<Store> getUserStores(String userId, Pageable pageable);
    public Page<Store> getStores(Pageable pageable);
}
