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
public class UpdateOrderDto{
    private String cartId;
    // For logged users
    private String userId;
    private String shippingAddressId;
    private String billingAddressId;
    // For guest users
    private CreateAddressDto shippingAddress;
    private CreateAddressDto billingAddress;
    //
    private String note;
    private List<CreateOrderDetailsDto> orderDetails;
}
