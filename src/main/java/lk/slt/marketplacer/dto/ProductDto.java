package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    //private String price;
    private String categoryId;
    private String units;
    private Boolean publish;
    private String brand;
    private Currency currency;
    private List<String> medias;
}
