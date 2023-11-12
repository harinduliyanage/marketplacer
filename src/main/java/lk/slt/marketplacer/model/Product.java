package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.Currency;
import lk.slt.marketplacer.util.DiscountType;
import lk.slt.marketplacer.util.ProductStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Product implements Serializable {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String brand;
    //@Lob
    private String description;
    private Double price;
    private Double units;
    @ManyToOne
    @JoinColumn(name = "store_id", updatable = false)
    @ToString.Exclude
    private Store store;
    //
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    private Double reOrderLevel;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    //
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private Double discountAmount;
    //
    @ElementCollection
    @CollectionTable(name = "products_videos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "videos")
    private List<String> videos;
    @ElementCollection
    @CollectionTable(name = "products_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "images")
    private List<String> images;
}
