package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.Currency;

import java.util.List;

public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String price;
    private String units;
    private Currency currency;
    private List<String> medias;
}
