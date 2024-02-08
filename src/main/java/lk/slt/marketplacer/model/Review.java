package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;

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
    //
    @Lob
    @Column(columnDefinition="TEXT")
    private String text;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @ToString.Exclude
    private User user;
    @ManyToOne
    @ToString.Exclude
    private Product product;
    //
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;
}
