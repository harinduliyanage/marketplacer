package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<CartItems> cartItems;
}
