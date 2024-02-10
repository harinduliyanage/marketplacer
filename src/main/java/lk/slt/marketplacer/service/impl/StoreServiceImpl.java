package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import lk.slt.marketplacer.exceptions.*;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.QStore;
import lk.slt.marketplacer.model.Store;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.repository.StoreRepository;
import lk.slt.marketplacer.service.CategoryService;
import lk.slt.marketplacer.service.StoreService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.CategoryType;
import lk.slt.marketplacer.util.Constants;
import lk.slt.marketplacer.util.StoreStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author harindu.sul@gmail.com
 */
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {
    @Autowired
    private UserService userService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Store createStore(String userId, String categoryId, Store store) {
        String name = store.getName();
        //
        if (isNameAlreadyExists(name)) {
            throw new StoreAlreadyExistsException(String.format(Constants.STORE_ALREADY_EXISTS_MSG, "name", name));
        } else {
            String id = store.getId();
            if (id != null) {
                // Validate UUID format
                try {
                    UUID.fromString(id);
                } catch (IllegalArgumentException exception) {
                    throw new StoreIdInvalidException(String.format(Constants.INVALID_STORE_ID_MSG, id));
                }
                // Check user given id already using
                if (isIdAlreadyExists(id)) {
                    throw new StoreAlreadyExistsException(String.format(Constants.STORE_ALREADY_EXISTS_MSG, "id", id));
                }
            } else {
                //set uuid
                store.setId(UUID.randomUUID().toString());
            }
            //
            User user = userService.getUser(userId);
            Category category = categoryService.getCategoryById(categoryId);
            if (category.getCategoryType() != CategoryType.STORE) {
                throw new CategoryTypeInvalidException(String.format(Constants.INVALID_CATEGORY_TYPE_MSG, categoryId));
            }
            store.setUser(user);
            store.setStoreStatus(StoreStatus.IN_REVIEW);
            store.setCategory(category);
            //
            Store savedStore = storeRepository.save(store);
            log.info("store has been successfully created {}", savedStore);
            return savedStore;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Store getStore(String userId, String storeId) {
        User foundUser = userService.getUser(userId);
        //
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.user.eq(foundUser).and(qStore.id.eq(storeId));
        Optional<Store> found = storeRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new StoreNotFoundException(String.format(Constants.STORE_NOT_FOUND_OF_USER_MSG, storeId, userId));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Store updateStore(String userId, String storeId, String categoryId, Store store) {
        String name = store.getName();
        Store foundStore = getStore(userId, storeId);
        //
        if (isNameAlreadyExists(store.getId(), name)) {
            throw new StoreAlreadyExistsException(String.format(Constants.STORE_ALREADY_EXISTS_MSG, "name", name));
        }else if(foundStore.getStoreStatus().equals(StoreStatus.IN_REVIEW)  && (store.getStoreStatus().equals(StoreStatus.PUBLISHED) || store.getStoreStatus().equals(StoreStatus.UNPUBLISHED))) {
            throw new StoreStatusInvalidException(String.format(Constants.STORE_STATUS_INVALID_MSG, store.getStoreStatus()));
        } else {
            if (categoryId != null) {
                Category category = categoryService.getCategoryById(categoryId);
                if (category.getCategoryType() != CategoryType.STORE) {
                    throw new CategoryTypeInvalidException(String.format(Constants.INVALID_CATEGORY_TYPE_MSG, categoryId));
                }
                store.setCategory(category);
            }
            store.setId(storeId);

            Store updatedStore = storeRepository.save(store);
            log.info("Store has been successfully updated {}", updatedStore);
            return updatedStore;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Store removeStore(String userId, String storeId) {
        Store store = getStore(userId, storeId);
        //
        storeRepository.deleteById(storeId);
        return store;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Store> getUserStores(String userId, Pageable pageable) {
        User user = userService.getUser(userId);
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.user.eq(user);
        return storeRepository.findAll(expression, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Store> getStores(StoreStatus storeStatus, Pageable pageable) {
        if(storeStatus==null) {
            return storeRepository.findAll(pageable);
        }else{
            QStore qStore = QStore.store;
            BooleanExpression expression = qStore.storeStatus.eq(storeStatus);
            return storeRepository.findAll(expression, pageable);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Store getStoreById(String storeId) {
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.id.eq(storeId);
        Optional<Store> found = storeRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new StoreNotFoundException(String.format(Constants.STORE_NOT_FOUND_MSG, storeId));
        }
    }

    private Boolean isNameAlreadyExists(String name) {
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.name.eq(name);
        return storeRepository.exists(expression);
    }

    private Boolean isNameAlreadyExists(String storeId, String name) {
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.name.eq(name).and(qStore.id.ne(storeId));
        return storeRepository.exists(expression);
    }

    private Boolean isIdAlreadyExists(String storeId) {
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.id.eq(storeId);
        return storeRepository.exists(expression);
    }
}
