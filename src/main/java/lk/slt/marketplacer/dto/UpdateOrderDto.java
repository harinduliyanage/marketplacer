package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.OrderStatus;
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
    private OrderStatus orderStatus;
    // For logged users
    private String shippingAddressId;
    private String billingAddressId;
    // For guest users
    private CreateAddressDto shippingAddress;
    private CreateAddressDto billingAddress;
    //
    private String note;
}
