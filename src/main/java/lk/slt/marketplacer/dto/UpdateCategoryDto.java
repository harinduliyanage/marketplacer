package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.slt.marketplacer.util.CategoryStatus;
import lk.slt.marketplacer.util.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDto {
    private String name;
    private String parentCategoryId;
    private String imageUrl;
    private String icon;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;
    private Boolean isFeatured;
}
