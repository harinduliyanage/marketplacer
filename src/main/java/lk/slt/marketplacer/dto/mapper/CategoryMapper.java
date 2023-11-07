package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateCategoryDto;
import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.UpdateCategoryDto;
import lk.slt.marketplacer.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    //
    public abstract CategoryDto categoryToCategoryDto(Category category);
    public abstract  Category categoryDtoToCategory(CategoryDto categoryDto);
    @Mapping(target = "id", ignore = true)
    public abstract Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);
    @Mapping(target = "id", ignore = true)
    public abstract Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto);
    public abstract List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);
}
