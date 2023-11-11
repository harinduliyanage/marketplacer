package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateCategoryDto;
import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.UpdateCategoryDto;
import lk.slt.marketplacer.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    //
    public CategoryDto categoryToCategoryDto(Category category) {
        CategoryDto target = new CategoryDto();
        target.setId(category.getId());
        target.setName(category.getName());
        target.setCategoryType(category.getCategoryType());
        //
        if (null != category.getParentCategory()) {
            target.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        if (null != category.getSubCategories() && !category.getSubCategories().isEmpty()) {
            target.setSubCategories(mapSubCategories(category.getSubCategories()));
        }
        return target;
    }

    public abstract Category categoryDtoToCategory(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    public abstract Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);

    @Mapping(target = "id", ignore = true)
    public abstract Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto);

    public abstract List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);

    private List<CategoryDto> mapSubCategories(List<Category> subCategories) {
        return subCategories.stream()
                .map(sub -> CategoryDto.builder()
                        .id(sub.getId())
                        .name(sub.getName())
                        .categoryType(sub.getCategoryType())
                        .build())
                .collect(Collectors.toList());
    }

    private CategoryDto mapParentCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        if (category.getParentCategory() == null) {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setCategoryType(category.getCategoryType());
        } else {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setCategoryType(category.getCategoryType());
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return categoryDto;
    }
}
