package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.CategoryStatus;
import lk.slt.marketplacer.util.CategoryType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    private String id;

    private String name;
    private CategoryType categoryType;
    private CategoryStatus categoryStatus;
    private String imageUrl;
    private String icon;
    @ManyToOne
    private Category parentCategory;
    private Boolean isFeatured;
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Category> subCategories;
}
