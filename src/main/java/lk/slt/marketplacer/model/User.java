package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

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
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
