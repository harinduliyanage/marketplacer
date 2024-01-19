package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateCategoryDto;
import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.UpdateCategoryDto;
import lk.slt.marketplacer.model.Category;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Named("categoryToCategoryDto")
    public CategoryDto categoryToCategoryDto(Category category) {
        CategoryDto target = new CategoryDto();
        target.setId(category.getId());
        target.setName(category.getName());
        target.setImageUrl(category.getImageUrl());
        target.setCategoryStatus(category.getCategoryStatus());
        target.setIcon(category.getIcon());
        target.setCategoryType(category.getCategoryType());
        target.setIsFeatured(category.getIsFeatured());
        //
        if (null != category.getParentCategory()) {
            CategoryDto parentCategory = CategoryDto.builder()
                    .id(category.getParentCategory().getId())
                    .name(category.getParentCategory().getName())
                    .categoryType(category.getParentCategory().getCategoryType())
                    .isFeatured(category.getParentCategory().getIsFeatured())
                    .build();

            target.setParentCategory(parentCategory);
        }
        if (null != category.getSubCategories() && !category.getSubCategories().isEmpty()) {
            target.setSubCategories(mapSubCategories(category.getSubCategories()));
        }
        return target;
    }

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "categoryStatus", ignore = true)
    public abstract Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "categoryType", source = "categoryType",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "imageUrl", source = "imageUrl",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "icon", source = "icon",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "categoryStatus", source = "categoryStatus",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "isFeatured", source = "isFeatured",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto, @MappingTarget Category category);

    @IterableMapping(qualifiedByName = "categoryToCategoryDto")
    public abstract List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);

    /**
     * Get parents tree
     *
     * @param category
     * @return
     */
    public CategoryDto mapCategoryWithParents(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        //
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setCategoryType(category.getCategoryType());
        categoryDto.setImageUrl(category.getImageUrl());
        categoryDto.setCategoryStatus(category.getCategoryStatus());
        categoryDto.setIcon(category.getIcon());
        categoryDto.setIsFeatured(category.getIsFeatured());
        if (null != category.getParentCategory()) {
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return categoryDto;
    }

    //
    private List<CategoryDto> mapSubCategories(List<Category> subCategories) {
        return subCategories.stream()
                .map(sub -> CategoryDto.builder()
                        .id(sub.getId())
                        .name(sub.getName())
                        .subCategories(mapSubCategories(sub.getSubCategories()))
                        .categoryType(sub.getCategoryType())
                        .isFeatured(sub.getIsFeatured())
                        .build())
                .collect(Collectors.toList());
    }

    private CategoryDto mapParentCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        //
        if (category.getParentCategory() == null) {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setIsFeatured(category.getIsFeatured());
            categoryDto.setCategoryType(category.getCategoryType());
        } else {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setIsFeatured(category.getIsFeatured());
            categoryDto.setCategoryType(category.getCategoryType());
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return categoryDto;
    }
}
