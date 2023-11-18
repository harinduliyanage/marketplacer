package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateOrderDetailsDto;
import lk.slt.marketplacer.dto.UpdateOrderDetailsDto;
import lk.slt.marketplacer.dto.OrderDetailsDto;
import lk.slt.marketplacer.model.OrderDetails;
import lk.slt.marketplacer.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProductService.class })
public interface OrderDetailsMapper {
    OrderDetailsDto orderDetailsToOrderDetailsDto(OrderDetails orderDetails);

    OrderDetails orderDetailsDtoToOrderDetails(OrderDetailsDto orderDetailsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "productId", target = "product")
    OrderDetails createOrderDetailsDtoToOrderDetails(CreateOrderDetailsDto createOrderDetailsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", source = "price",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "units", source = "units",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "discount", source = "discount",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetails updateOrderDetailsDtoToOrderDetails(
            UpdateOrderDetailsDto updateOrderDetailsDto, @MappingTarget OrderDetails orderDetails);

    List<OrderDetailsDto> orderDetailsListToOrderDetailsDtoList(List<OrderDetails> orderDetailsList);
}
