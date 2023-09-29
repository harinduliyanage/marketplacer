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
public class CreateStoreDto {

    private List<SocialLinkDto> socialLinks;
    //
    private String name;
    private String description;
    //
    private String telephone;
    private String fax;
    private String address;
    private String email;
    private String website;
}
