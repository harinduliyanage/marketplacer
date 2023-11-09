package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.CategoryController;
import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.dto.mapper.CategoryMapper;
import lk.slt.marketplacer.model.Category;
import lk.slt.marketplacer.service.CategoryService;
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
        return categoryMapper.categoryToCategoryDto(categoryService.
                createCategory(createCategoryDto.getParentCategoryId(), categoryMapper.
                        createCategoryDtoToCategory(createCategoryDto)
                )
        );
    }

    @Override
    public ListResponseDto<CategoryDto> getCategories(String parentCategoryId, Pageable pageable) {
        Page<Category> page = categoryService.getCategories(parentCategoryId, pageable);
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
        return categoryMapper.categoryToCategoryDto(categoryService.getCategoryById(categoryId));
    }

    @Override
    public CategoryDto updateCategory(String categoryId, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryMapper.updateCategoryDtoToCategory(updateCategoryDto);
        category.setId(categoryId);
        return categoryMapper.categoryToCategoryDto(
                categoryService.updateCategory(updateCategoryDto.getParentCategoryId(), categoryId,
                        category)
        );
    }

    @Override
    public CategoryDto removeCategory(String categoryId) {
        return categoryMapper.categoryToCategoryDto(
                categoryService.removeCategory(categoryId)
        );
    }
}
