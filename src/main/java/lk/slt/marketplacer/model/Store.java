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
@Table(name = "stores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    @Id
    @UuidGenerator
    private String id;
    //
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY)
    private List<SocialLink> socialLinks;
    //
    private String name;
    private String description;
    //
    private String telephone;
    private String fax;
    private String address;
    private String email;
    private String website;
    //

}