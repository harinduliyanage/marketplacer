package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.CategoryType;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
    @UuidGenerator
    private String id;

    private String name;
    private String imageUrl;
    private CategoryType categoryType;

    @ManyToOne
    private Category parentCategory;

    private Boolean isFeatured;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Category> subCategories;
}
