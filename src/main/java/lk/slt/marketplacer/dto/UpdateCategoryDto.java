package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.slt.marketplacer.util.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    private String parentCategoryId;
}
