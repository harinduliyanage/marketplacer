package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreDto {

    private List<SocialLinkDto> socialLinks;
    //
    @NotEmpty
    private String name;
    private String description;
    //
    private String telephone;
    private String fax;
    private String address;
    private String brFilePath;
    private String logoFilePath;
    @Email
    private String email;
    @URL
    private String website;
}
