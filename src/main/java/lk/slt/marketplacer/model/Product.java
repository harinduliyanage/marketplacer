package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.Currency;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "store_id", updatable = false)
    @ToString.Exclude
    private Store store;

    private String name;
    //@Lob
    private String description;
    //private Double price;
    private Double units;
    private Boolean publish;
    private String brand;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ElementCollection
    @CollectionTable(name = "products_medias", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "media")
    private List<String> medias;
}
