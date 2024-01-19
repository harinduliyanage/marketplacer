package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<Product> products;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;
}
