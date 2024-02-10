package lk.slt.marketplacer.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "cart-items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItems implements Serializable {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", updatable = false)
    @ToString.Exclude
    private Product product;
    //
    private Double units;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;
}
