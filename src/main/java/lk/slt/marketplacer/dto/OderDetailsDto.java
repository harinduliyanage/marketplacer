package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OderDetailsDto {
    private String id;
    private Product product;
    private Double price;
    private Double units;
    private Double discount;
}
