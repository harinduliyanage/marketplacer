package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Store;

public interface StoreService {

    public Store createStore(String userId, Store store);
    public Store getStore(String userId, String storeId);
}
