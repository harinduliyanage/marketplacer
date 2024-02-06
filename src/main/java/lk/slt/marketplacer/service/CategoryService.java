package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.util.CategoryStatus;
import lk.slt.marketplacer.util.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Category createCategory(String parentId, Category category);

    public Category getCategoryById(String id);

    public Page<Category> getCategories(String parentCategoryId, String categoryName, CategoryType categoryType, CategoryStatus categoryStatus, Pageable pageable);

    public Category updateCategory(String parentId, String id, Category category);

    public Category removeCategory(String id);
}
