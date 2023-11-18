package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateOrderDto;
import lk.slt.marketplacer.dto.OrderDto;
import lk.slt.marketplacer.dto.UpdateOrderDto;
import lk.slt.marketplacer.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = { OrderDetailsMapper.class })
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "id", ignore = true)
    Order createOrderDtoToOrder(CreateOrderDto createOrderDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderDetails", source = "orderDetails",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order updateOrderDtoToOrder(
            UpdateOrderDto updateOrderDto, @MappingTarget Order order);

    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
