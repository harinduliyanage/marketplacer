package lk.slt.marketplacer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Entity
@Table(name = "social_links")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink implements Serializable {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String link;
    private String icon;
}
