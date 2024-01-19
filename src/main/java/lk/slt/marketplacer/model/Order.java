package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
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
    @ToString.Exclude
    private User user;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private Address billingAddress;

    private String note;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<OrderDetails> orderDetails;
}
