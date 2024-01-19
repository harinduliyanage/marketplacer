package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.CategoryStatus;
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
    private String imageUrl;
    private String icon;
    private CategoryStatus categoryStatus;
    private CategoryType categoryType;
    private CategoryDto parentCategory;
    private Boolean isFeatured;
    private List<CategoryDto> subCategories;
}
