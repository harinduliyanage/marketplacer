package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> followedStoreIds;
}
