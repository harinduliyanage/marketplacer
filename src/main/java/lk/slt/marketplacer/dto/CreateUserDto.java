package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthDay;
}
