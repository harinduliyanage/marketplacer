package lk.slt.marketplacer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String sub;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthDay;
    private CartDto cart;
    private List<StoreDto> flowedStores;
}
