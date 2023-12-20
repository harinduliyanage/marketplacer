package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.slt.marketplacer.util.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressDto {
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String country;
    private String provinceOrState;
    private String contactNumber;
}
