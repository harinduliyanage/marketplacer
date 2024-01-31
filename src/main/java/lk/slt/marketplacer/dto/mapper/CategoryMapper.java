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
        target.setIconUrl(category.getIconUrl());
        target.setCategoryStatus(category.getCategoryStatus());
        target.setCategoryType(category.getCategoryType());
        target.setIsFeatured(category.getIsFeatured());
        target.setLastUpdatedAt(category.getLastUpdatedAt());
        target.setCreatedAt(category.getCreatedAt());
        //
        if (null != category.getParentCategory()) {
            CategoryDto parentCategory = CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .imageUrl(category.getImageUrl())
                    .iconUrl(category.getIconUrl())
                    .categoryType(category.getCategoryType())
                    .categoryStatus(category.getCategoryStatus())
                    .isFeatured(category.getIsFeatured())
                    .createdAt(category.getCreatedAt())
                    .lastUpdatedAt(category.getLastUpdatedAt())
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
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    public abstract Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    public abstract Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto);

    @IterableMapping(qualifiedByName = "categoryToCategoryDto")
    public abstract List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);

    /**
     * Get parents tree
     *
     * @param category
     * @return
     */
    public CategoryDto mapCategoryWithParents(Category category) {
        CategoryDto target = new CategoryDto();
        //
        target.setId(category.getId());
        target.setName(category.getName());
        target.setCategoryType(category.getCategoryType());
        target.setImageUrl(category.getImageUrl());
        target.setIconUrl(category.getIconUrl());
        target.setCategoryType(category.getCategoryType());
        target.setCategoryStatus(category.getCategoryStatus());
        target.setIsFeatured(category.getIsFeatured());
        target.setLastUpdatedAt(category.getLastUpdatedAt());
        target.setCreatedAt(category.getCreatedAt());
        if (null != category.getParentCategory()) {
            target.setParentCategory(mapCategoryWithParents(category.getParentCategory()));
        }
        return target;
    }

    //
    private List<CategoryDto> mapSubCategories(List<Category> subCategories) {
        return subCategories.stream()
                .map(sub -> CategoryDto.builder()
                        .id(sub.getId())
                        .name(sub.getName())
                        .imageUrl(sub.getImageUrl())
                        .iconUrl(sub.getIconUrl())
                        .subCategories(mapSubCategories(sub.getSubCategories()))
                        .categoryType(sub.getCategoryType())
                        .categoryStatus(sub.getCategoryStatus())
                        .isFeatured(sub.getIsFeatured())
                        .createdAt(sub.getCreatedAt())
                        .lastUpdatedAt(sub.getLastUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
