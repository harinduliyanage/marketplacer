package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.Currency;
import lk.slt.marketplacer.util.DiscountType;
import lk.slt.marketplacer.util.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String brand;
    private String description;
    private String specification;
    private Double price;
    private Double units;
    //
    private Double reOrderLevel;
    private CategoryDto category;
    private ProductStatus productStatus;
    private Currency currency;
    //
    private DiscountType discountType;
    private Double discountAmount;
    //
    private List<String> videos;
    private List<String> images;
    private Set<String> tags;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
