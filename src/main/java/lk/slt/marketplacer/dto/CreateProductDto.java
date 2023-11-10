package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotEmpty;
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
    //private String price;
    @NotEmpty
    private String categoryId;
    private String units;
    private Boolean publish;
    private String brand;
    private Currency currency;
    private List<String> videos;
    private List<String> images;
}
