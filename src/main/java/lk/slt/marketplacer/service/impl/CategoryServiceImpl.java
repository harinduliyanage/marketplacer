package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.*;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.QCategory;
import lk.slt.marketplacer.repository.CategoryRepository;
import lk.slt.marketplacer.service.CategoryService;
import lk.slt.marketplacer.util.CategoryStatus;
import lk.slt.marketplacer.util.CategoryType;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String parentId, Category category) {
        if (parentId != null) {
            Category foundParent = getCategoryById(parentId);
            category.setParentCategory(foundParent);
        }
        //
        if (isNameAlreadyExists(category.getParentCategory(), category.getName(), category.getCategoryType())) {
            throw new CategoryAlreadyExistsException(String
                    .format(Constants.CATEGORY_NAME_ALREADY_EXISTS_MSG, category.getName())
            );
        } else {
            String id = category.getId();
            if (null != id) {
                // Validate UUID format
                try {
                    UUID.fromString(id);
                } catch (IllegalArgumentException exception) {
                    throw new CategoryIdInvalidException(String.format(Constants.INVALID_CATEGORY_ID_MSG, id));
                }
                // Check user given id already using
                if (isIdAlreadyExists(id)) {
                    throw new CategoryAlreadyExistsException(String.format(Constants.CATEGORY_ID_ALREADY_EXISTS_MSG, "id", id));
                }
            } else {
                category.setId(UUID.randomUUID().toString());
            }
            category.setCategoryStatus(CategoryStatus.IN_REVIEW);
            Category savedCategory = categoryRepository.save(category);
            log.info("category has been successfully created {}", savedCategory);
            //
            return savedCategory;
        }
    }

    @Override
    public Category getCategoryById(String id) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression = qCategory.id.eq(id).and(qCategory.categoryStatus.eq(CategoryStatus.APPROVED));
        Optional<Category> found = categoryRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new CategoryNotFoundException(String.format(Constants.CATEGORY_NOT_FOUND_MSG, id));
        }
    }

    @Override
    public Page<Category> getCategories(String parentCategoryId, String categoryName, CategoryType categoryType, CategoryStatus categoryStatus, Pageable pageable) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression;
        //
        if (parentCategoryId != null && categoryType != null && categoryName != null && categoryStatus != null) {
            expression = qCategory.categoryType.eq(categoryType).and(qCategory.categoryStatus.eq(categoryStatus)).and(qCategory.name.eq(categoryName))
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (parentCategoryId != null && categoryType != null && categoryName != null) {
            expression = qCategory.categoryType.eq(categoryType).and(qCategory.name.eq(categoryName))
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (parentCategoryId != null && categoryType != null && categoryStatus != null) {
            expression = qCategory.categoryType.eq(categoryType).and(qCategory.categoryStatus.eq(categoryStatus))
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (categoryStatus != null && categoryType != null && categoryName != null) {
            expression = qCategory.categoryType.eq(categoryType).and(qCategory.name.eq(categoryName))
                    .and(qCategory.categoryStatus.eq(categoryStatus));
        } else if (parentCategoryId != null && categoryName != null && categoryStatus != null) {
            expression = qCategory.categoryStatus.eq(categoryStatus).and(qCategory.name.eq(categoryName))
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (parentCategoryId != null && categoryStatus != null) {
            expression = qCategory.categoryStatus.eq(categoryStatus)
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (categoryName != null && categoryStatus != null) {
            expression = qCategory.categoryStatus.eq(categoryStatus)
                    .and(qCategory.name.eq(categoryName));
        } else if (categoryType != null && categoryStatus != null) {
            expression = qCategory.categoryStatus.eq(categoryStatus)
                    .and(qCategory.categoryType.eq(categoryType));
        } else if (parentCategoryId != null && categoryType != null) {
            expression = qCategory.categoryType.eq(categoryType)
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (parentCategoryId != null && categoryName != null) {
            expression = qCategory.name.eq(categoryName)
                    .and(parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId)));
        } else if (categoryName != null && categoryType != null) {
            expression = qCategory.categoryType.eq(categoryType)
                    .and(qCategory.name.eq(categoryName));
        } else if (parentCategoryId != null) {
            expression = parentCategoryId.equals("null") ? qCategory.parentCategory.isNull() : qCategory.parentCategory.eq(getCategoryById(parentCategoryId));
        } else if (categoryType != null) {
            expression = qCategory.categoryType.eq(categoryType);
        } else if (categoryName != null) {
            expression = qCategory.name.eq(categoryName);
        } else if (categoryStatus != null) {
            expression = qCategory.categoryStatus.eq(categoryStatus);
        } else {
            return categoryRepository.findAll(pageable);
        }
        //
        Page<Category> categories = categoryRepository.findAll(expression, pageable);
        return categoryStatus == CategoryStatus.APPROVED ? filterApprovedSubCategories(categories) : categories;
    }

    @Override
    public Category updateCategory(String parentId, String id, Category category) {
        Category found = getCategory(id);
        String name = category.getName();
        //
        if (null == category.getCategoryType()) {
            category.setCategoryType(found.getCategoryType());
        }
        if (null == name) {
            category.setName(found.getName());
        } else {
            if (!name.equals(found.getName()) && isNameAlreadyExists(category.getParentCategory(), name, category.getCategoryType())) {
                throw new CategoryAlreadyExistsException(String.format(Constants.CATEGORY_NAME_ALREADY_EXISTS_MSG, name));
            }
        }
        if (null == parentId) {
            category.setParentCategory(found.getParentCategory());
        } else {
            Category foundParent = getCategoryById(parentId);
            category.setParentCategory(foundParent);
        }
        if (null == category.getImageUrl()) {
            category.setImageUrl(found.getImageUrl());
        }
        if (null == category.getIconUrl()) {
            category.setIconUrl(found.getIconUrl());
        }
        if (null == category.getCategoryStatus()) {
            category.setCategoryStatus(found.getCategoryStatus());
        }
        if (null == category.getIsFeatured()) {
            category.setIsFeatured(found.getIsFeatured());
        }
        category.setId(found.getId());
        category.setCreatedAt(found.getCreatedAt());
        Category updatedCategory = categoryRepository.save(category);
        log.info("category has been successfully updated {}", updatedCategory);
        //
        return updatedCategory;

    }

    @Override
    public Category removeCategory(String id) {
        Category found = getCategory(id);
        categoryRepository.deleteById(id);
        log.info("category has been successfully deleted {}", found);
        //
        return found;
    }

    private Category getCategory(String id) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression = qCategory.id.eq(id);
        Optional<Category> found = categoryRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new CategoryNotFoundException(String.format(Constants.CATEGORY_NOT_FOUND_MSG, id));
        }
    }

    private Boolean isNameAlreadyExists(Category parentCategory, String name, CategoryType categoryType) {
        System.out.println(name);
        System.out.println(categoryType);
        QCategory qCategory = QCategory.category;
        BooleanExpression expression;
        //
        if (parentCategory == null) {
            expression = qCategory.name.eq(name)
                    .and(qCategory.categoryType.eq(categoryType))
                    .and(qCategory.parentCategory.isNull());
        } else {
            expression = qCategory.name.eq(name)
                    .and(qCategory.categoryType.eq(categoryType))
                    .and(qCategory.parentCategory.eq(parentCategory));
        }
        return categoryRepository.exists(expression);
    }

    private Boolean isIdAlreadyExists(String storeId) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression = qCategory.id.eq(storeId);
        return categoryRepository.exists(expression);
    }

    private Page<Category> filterApprovedSubCategories(Page<Category> categories) {
        return categories.map(category -> {
            List<Category> subCategories = category.getSubCategories().stream().filter(category1 -> category1.getCategoryStatus() == CategoryStatus.APPROVED).toList();
            category.setSubCategories(subCategories);
            return category;
        });
    }
}
