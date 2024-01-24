package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateOrderDto;
import lk.slt.marketplacer.dto.OrderDto;
import lk.slt.marketplacer.dto.UpdateOrderDto;
import lk.slt.marketplacer.model.Order;
import lk.slt.marketplacer.service.AddressService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderDetailsMapper.class, AddressMapper.class})
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    Order createOrderDtoToOrder(CreateOrderDto createOrderDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "billingAddress", source = "billingAddress",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "shippingAddress", source = "shippingAddress",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "orderStatus", source = "orderStatus",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "note", source = "note",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order updateOrderDtoToOrder(
            UpdateOrderDto updateOrderDto, @MappingTarget Order order);

    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
