package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    private String id;
    private String userId;
    private String cartId;
    @NotNull
    private CreateAddressDto shippingAddress;
    @NotNull
    private CreateAddressDto billingAddress;
    private String note;
    private List<CreateOrderDetailsDto> orderDetails;
}
