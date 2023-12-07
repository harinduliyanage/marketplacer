package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.CreateOrderDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.OrderDto;
import lk.slt.marketplacer.dto.UpdateOrderDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "order-services")
@RequestMapping("/api/v1")
public interface OrderController {
    @PostMapping(value = "/orders", consumes = {"application/json"}, produces = {"application/json"})
    public OrderDto createOrder(@Valid @RequestBody CreateOrderDto createOrderDto);

    @GetMapping(value = "/users/{userId}/orders", produces = {"application/json"})
    public ListResponseDto<OrderDto> getOrders(@PathVariable("userId") String userId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/users/{userId}/orders/{orderId}", produces = {"application/json"})
    public OrderDto getOrder(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId);

    @PatchMapping(value = "/users/{userId}/orders/{orderId}", consumes = {"application/json"}, produces = {"application/json"})
    public OrderDto updateOrder(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId, @Valid @RequestBody UpdateOrderDto updateOrderDto);

    @DeleteMapping(value = "/users/{userId}/orders/{orderId}", produces = {"application/json"})
    public OrderDto removeOrder(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId);
}
