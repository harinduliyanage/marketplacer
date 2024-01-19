package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * @author harindu.sul@gmail.com
 */
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @UuidGenerator
    private String id;

    private String sub;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String birthDay;
    @ToString.Exclude
    @OneToOne
    private Cart cart;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Wishlist wishlist;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Store> followedStores;
}
