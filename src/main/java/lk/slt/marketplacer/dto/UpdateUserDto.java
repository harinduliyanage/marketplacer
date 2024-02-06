package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lk.slt.marketplacer.util.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private String username;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthDay;
    private Set<String> followedStoreIds;
}
