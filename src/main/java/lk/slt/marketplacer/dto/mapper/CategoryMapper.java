package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateCategoryDto;
import lk.slt.marketplacer.dto.CategoryDto;
import lk.slt.marketplacer.dto.UpdateCategoryDto;
import lk.slt.marketplacer.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );
    //
    CategoryDto categoryToCategoryDto(Category category);
    Category categoryDtoToCategory(CategoryDto categoryDto);
    @Mapping(target = "id", ignore = true)
    Category createCategoryDtoToCategory(CreateCategoryDto createCategoryDto);
    @Mapping(target = "id", ignore = true)
    Category updateCategoryDtoToCategory(UpdateCategoryDto updateCategoryDto);
    List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList);
}
