package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lk.slt.marketplacer.util.Currency;
import lk.slt.marketplacer.util.DiscountType;
import lk.slt.marketplacer.util.ProductStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    private String name;
    private String brand;
    private String description;
    private String specification;
    private Double price;
    private Double units;
    private Double reOrderLevel;
    private String categoryId;
    private Currency currency;

    private DiscountType discountType;
    private Double discountAmount;
    private List<String> videos;
    private List<String> images;
}
