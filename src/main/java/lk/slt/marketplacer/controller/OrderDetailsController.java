package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.CreateOrderDetailsDto;
import lk.slt.marketplacer.dto.OrderDetailsDto;
import lk.slt.marketplacer.dto.UpdateOrderDetailsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "order-details-services")
@RequestMapping("/api/v1/users/{userId}/orders/{orderId}")
public interface OrderDetailsController {
    @GetMapping(value = "/order-details", produces = {"application/json"})
    public List<OrderDetailsDto> getOrderDetails(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId);

    @GetMapping(value = "/order-details/{orderDetailsId}", produces = {"application/json"})
    public OrderDetailsDto getOrderDetail(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId, @PathVariable("orderDetailsId") String orderDetailsId);
}
