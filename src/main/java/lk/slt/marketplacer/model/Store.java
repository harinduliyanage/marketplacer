package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lk.slt.marketplacer.util.StoreStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "stores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    @Id
    private String id;
    //
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SocialLink> socialLinks;
    //
    private String name;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    //
    private String telephone;
    private String fax;
    private String address;
    private String email;
    private String website;
    private String brFilePath;
    private String logoFilePath;
    //
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;

}
