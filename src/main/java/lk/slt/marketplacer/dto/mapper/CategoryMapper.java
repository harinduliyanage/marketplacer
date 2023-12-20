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
        target.setCategoryType(category.getCategoryType());
        //
        if (null != category.getParentCategory()) {
            CategoryDto parentCategory = CategoryDto.builder()
                    .id(category.getParentCategory().getId())
                    .name(category.getParentCategory().getName())
                    .categoryType(category.getCategoryType())
                    .build();

            target.setParentCategory(parentCategory);
        }
        if (null != category.getSubCategories() && !category.getSubCategories().isEmpty()) {
            target.setSubCategories(mapSubCategories(category.getSubCategories()));
        }
        return target;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    public abstract Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "categoryType", source = "categoryType",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    public abstract Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto, @MappingTarget Category category);

    @IterableMapping(qualifiedByName = "categoryToCategoryDto")
    public abstract List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);

    public CategoryDto mapCategoryWithParents(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        //
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setCategoryType(category.getCategoryType());
        if (null != category.getParentCategory()) {
            categoryDto.setParentCategory(mapParentCategory(category.getParentCategory()));
        }
        return  categoryDto;
    }
    //
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
        //
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
