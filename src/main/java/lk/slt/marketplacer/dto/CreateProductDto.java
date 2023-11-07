package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lk.slt.marketplacer.util.Currency;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    @NotEmpty
    private String name;
    private String description;
    @PositiveOrZero
    //private String price;
    private String units;
    private Boolean publish;
    private String brand;
    private Currency currency;
    private List<String> medias;
}
