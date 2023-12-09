package lk.slt.marketplacer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    public User copy() {
        User newUser = new User();
        //
        newUser.setId(this.id);
        newUser.setSub(this.sub);
        newUser.setFirstName(this.firstName);
        newUser.setLastName(this.lastName);
        newUser.setUsername(this.username);
        newUser.setEmail(this.email);
        newUser.setPhone(this.phone);
        newUser.setBirthDay(this.birthDay);
        newUser.setAddresses(this.addresses);
        //
        return newUser;
    }
}
