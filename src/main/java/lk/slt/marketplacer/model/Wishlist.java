package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Product> products;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;
}
