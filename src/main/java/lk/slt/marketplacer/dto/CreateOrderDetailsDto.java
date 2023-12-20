package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDetailsDto {
    @NotEmpty
    private String productId;
    @NotEmpty
    private Double units;
}
