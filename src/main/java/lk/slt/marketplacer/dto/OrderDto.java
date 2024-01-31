package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private OrderStatus orderStatus;
    private UserDto user;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
    private String note;
    private List<OrderDetailsDto> orderDetails;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
