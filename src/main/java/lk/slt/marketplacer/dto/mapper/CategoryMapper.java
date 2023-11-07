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
        //
        if (null!=category.getParentCategory()) {
            CategoryDto parentCategory = CategoryDto.builder()
                    .id(category.getParentCategory().getId())
                    .name(category.getParentCategory().getName()).build();

            target.setParentCategory(parentCategory);
        }
        if (null != category.getSubCategories() && category.getSubCategories().size() > 0)  {
            target.setSubCategories(mapSubCategories(category.getSubCategories()));
        }
        return target;
    }

    public abstract  Category categoryDtoToCategory(CategoryDto categoryDto);
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
                        .build())
                .collect(Collectors.toList());
    }
}
