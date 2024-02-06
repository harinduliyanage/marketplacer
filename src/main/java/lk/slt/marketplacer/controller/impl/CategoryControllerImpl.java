package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.CategoryController;
import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.dto.mapper.CategoryMapper;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.model.Order;
import lk.slt.marketplacer.service.CategoryService;
import lk.slt.marketplacer.util.CategoryStatus;
import lk.slt.marketplacer.util.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryControllerImpl implements CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        Category createdCategory = categoryService.createCategory(createCategoryDto.getParentCategoryId(),
                categoryMapper.createCategoryDtoToCategory(createCategoryDto));
        //
        return categoryMapper.categoryToCategoryDto(createdCategory);
    }

    @Override
    public ListResponseDto<CategoryDto> getCategories(String parentCategoryId, String categoryName, CategoryType categoryType, CategoryStatus categoryStatus, Pageable pageable) {
        Page<Category> page = categoryService.getCategories(parentCategoryId, categoryName, categoryType, categoryStatus, pageable);
        return ListResponseDto.<CategoryDto>builder()
                .data(categoryMapper.categoryListToCategoryDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryMapper.updateCategoryDtoToCategory(updateCategoryDto);
        System.out.println(category);
        Category updatedCategory = categoryService.updateCategory(updateCategoryDto.getParentCategoryId(), categoryId, category);
        //
        return categoryMapper.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public CategoryDto removeCategory(String categoryId) {
        Category category = categoryService.removeCategory(categoryId);
        return categoryMapper.categoryToCategoryDto(category);
    }
}
