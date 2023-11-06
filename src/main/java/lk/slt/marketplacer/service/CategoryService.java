package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Category createCategory(Category category);

    public Category getCategoryById(String id);

    public Page<Category> getCategories(Pageable pageable);

    public Category updateCategory(String id, Category category);

    public Category removeCategory(String id);
}
