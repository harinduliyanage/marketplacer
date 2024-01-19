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
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDto {
    private String id;
    private String parentCategoryId;
    private String imageUrl;
    private String icon;
    @NotEmpty
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    private Boolean isFeatured;
}
