package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.CategoryAlreadyExistsException;
import lk.slt.marketplacer.exceptions.CategoryNotFoundException;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.QCategory;
import lk.slt.marketplacer.repository.CategoryRepository;
import lk.slt.marketplacer.service.CategoryService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String parentId, Category category) {
        if (parentId != null) {
            Category foundParent = getCategoryById(parentId);
            category.setParentCategory(foundParent);
        }
        //
        if (isNameAlreadyExists(parentId, category.getName())) {
            throw new CategoryAlreadyExistsException(String.format(Constants.CATEGORY_ALREADY_EXISTS_MSG, category.getName()));
        } else {
            Category savedCategory = categoryRepository.save(category);
            log.info("category has been successfully created {}", savedCategory);
            //
            return savedCategory;
        }
    }

    @Override
    public Category getCategoryById(String id) {
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

    @Override
    public Page<Category> getCategories(String parentCategoryId, Pageable pageable) {
        if (parentCategoryId == null) {
            return categoryRepository.findAll(pageable);
        } else {
            Category found = getCategoryById(parentCategoryId);
            QCategory qCategory = QCategory.category;
            BooleanExpression expression = qCategory.parentCategory.eq(found);
            return categoryRepository.findAll(expression, pageable);
        }
    }

    @Override
    public Category updateCategory(String parentId, String id, Category category) {
        Category found = getCategoryById(id);
        String name = category.getName();
        //
        if (parentId != null) {
            Category foundParent = getCategoryById(parentId);
            category.setParentCategory(foundParent);
        }
        //
        if (!name.equals(found.getName()) && isNameAlreadyExists(parentId, name)) {
            throw new CategoryAlreadyExistsException(String.format(Constants.CATEGORY_ALREADY_EXISTS_MSG, name));
        } else {
            Category updatedCategory = categoryRepository.save(category);
            log.info("category has been successfully updated {}", updatedCategory);
            //
            return updatedCategory;
        }
    }

    @Override
    public Category removeCategory(String id) {
        Category found = getCategoryById(id);
        categoryRepository.deleteById(id);
        log.info("category has been successfully deleted {}", found);
        //
        return found;
    }

    private Boolean isNameAlreadyExists(String parentCategoryId, String name) {
        QCategory qCategory = QCategory.category;
        BooleanExpression expression;
        //
        if (parentCategoryId == null) {
            expression = qCategory.name.eq(name);
        } else {
            expression = qCategory.name.eq(name).and(qCategory.parentCategory.id.eq(parentCategoryId));
        }
        return categoryRepository.exists(expression);
    }
}
