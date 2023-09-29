package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private String id;
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
    //
    private StoreStatus storeStatus;
}
