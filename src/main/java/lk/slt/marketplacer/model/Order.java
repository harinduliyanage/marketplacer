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
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private Address billingAddress;

    private String note;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<OrderDetails> orderDetails;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;
}
