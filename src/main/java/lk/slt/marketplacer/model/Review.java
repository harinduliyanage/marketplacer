package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.AddressType;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {
    @Id
    @UuidGenerator
    private String id;

    private String text;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @ToString.Exclude
    private User user;
    @ManyToOne
    @ToString.Exclude
    private Product product;
}
