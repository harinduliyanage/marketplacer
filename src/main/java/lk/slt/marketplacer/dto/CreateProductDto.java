package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lk.slt.marketplacer.util.Currency;
import lk.slt.marketplacer.util.DiscountType;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    @NotEmpty
    private String name;
    private String brand;
    private String description;
    private String specification;
    private Double price;
    private Double units;
    private Double reOrderLevel;
    @NotEmpty
    private String categoryId;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    //
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private Double discountAmount;
    private List<String> videos;
    private List<String> images;
    private Set<String> tags;
}
