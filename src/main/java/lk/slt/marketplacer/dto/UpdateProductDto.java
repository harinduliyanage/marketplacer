package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Null;
import lk.slt.marketplacer.util.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductDto extends CreateProductDto {
    private String categoryId;
    private ProductStatus productStatus;
}
