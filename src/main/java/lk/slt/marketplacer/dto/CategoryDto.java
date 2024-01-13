package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String id;
    private String name;
    private CategoryType categoryType;
    private CategoryDto parentCategory;
    private String imageUrl;
    private Boolean isFeatured;
    private List<CategoryDto> subCategories;
}
