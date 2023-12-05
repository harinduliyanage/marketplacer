package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String id;
    private AddressType addressType;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String country;
    private String provinceOrState;
    private String contactNumber;
}
