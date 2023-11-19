package lk.slt.marketplacer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.slt.marketplacer.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    private String id;
    @JsonIgnore // @JsonIgnore annotation temporally added to product loading issue resolver
    private Product product;
    private Double price;
    private Double units;
    private Double discount;
}
