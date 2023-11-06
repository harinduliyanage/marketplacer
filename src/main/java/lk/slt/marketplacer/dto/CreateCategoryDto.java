package lk.slt.marketplacer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDto {
    private String id;
    private String name;
    private CreateCategoryDto parentCategory;
}
