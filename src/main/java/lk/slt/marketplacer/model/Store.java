package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.StoreStatus;
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
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
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
    private String filePath;
    //
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

}
