package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private String username;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthDay;
    private Set<String> followedStoreIds;
}
