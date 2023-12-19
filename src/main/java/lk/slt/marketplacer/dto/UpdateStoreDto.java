package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.Email;
import lk.slt.marketplacer.util.StoreStatus;
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
public class UpdateStoreDto{
    private List<SocialLinkDto> socialLinks;
    //
    private String name;
    private String description;
    private String categoryId;
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
    private StoreStatus storeStatus;
}
