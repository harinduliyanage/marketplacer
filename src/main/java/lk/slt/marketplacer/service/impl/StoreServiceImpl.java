package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.CategoryTypeInvalidException;
import lk.slt.marketplacer.exceptions.StoreAlreadyExistsException;
import lk.slt.marketplacer.exceptions.StoreIdInvalidException;
import lk.slt.marketplacer.exceptions.StoreNotFoundException;
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
        if (isNameAlreadyExists(store.getId(), name)) {
            throw new StoreAlreadyExistsException(String.format(Constants.STORE_ALREADY_EXISTS_MSG, name));
        } else {
            User user = userService.getUserById(userId);
            Category category = categoryService.getCategoryById(categoryId);
            if (category.getCategoryType() != CategoryType.STORE) {
                throw new CategoryTypeInvalidException(String.format(Constants.INVALID_CATEGORY_TYPE_MSG, categoryId));
            }
            //
            String id = store.getId();
            if (id != null) {
                // Validate UUID
                try {
                    UUID.fromString(id);
                    store.setId(id);
                } catch (IllegalArgumentException exception) {
                    throw new StoreIdInvalidException(String.format(Constants.INVALID_STORE_ID_MSG, id));
                }
            } else {
                store.setId(UUID.randomUUID().toString());
            }
            //
            store.setUser(user);
            store.setStoreStatus(StoreStatus.PENDING);
            store.setCategory(category);
            //
            Store savedStore = storeRepository.save(store);
            log.info("store has been successfully created {}", savedStore);
            return savedStore;
        }
    }

    @Override
    public Store getStore(String userId, String storeId) {
        User user = userService.getUserById(userId);
        //
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.user.eq(user).and(qStore.id.eq(storeId));
        Optional<Store> found = storeRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new StoreNotFoundException(String.format(Constants.STORE_NOT_FOUND_MSG, storeId, userId));
        }
    }

    @Override
    public Store updateStore(String userId, String storeId, String categoryId, Store store) {
        String name = store.getName();
        //
        if (isNameAlreadyExists(store.getId(), name)) {
            throw new StoreAlreadyExistsException(String.format(Constants.STORE_ALREADY_EXISTS_MSG, name));
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
    public Store removeStore(String userId, String storeId) {
        Store store = getStore(userId, storeId);
        //
        storeRepository.deleteById(storeId);
        return store;
    }

    @Override
    public Page<Store> getUserStores(String userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        QStore qStore = QStore.store;
        BooleanExpression expression = qStore.user.eq(user);
        return storeRepository.findAll(expression, pageable);
    }

    @Override
    public Page<Store> getStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    private Boolean isNameAlreadyExists(String storeId, String name) {
        QStore qStore = QStore.store;
        BooleanExpression expression = storeId == null ? qStore.name.eq(name)
                : qStore.name.eq(name).and(qStore.id.ne(storeId));
        return storeRepository.exists(expression);
    }
}
