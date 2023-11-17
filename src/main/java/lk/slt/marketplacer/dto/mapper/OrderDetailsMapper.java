package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateOrderDetailsDto;
import lk.slt.marketplacer.dto.UpdateOrderDetailsDto;
import lk.slt.marketplacer.dto.OrderDetailsDto;
import lk.slt.marketplacer.model.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDetailsDto OrderDetailsToOrderDetailsDto(OrderDetails OrderDetails);

    OrderDetails OrderDetailsDtoToOrderDetails(OrderDetailsDto OrderDetailsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderDetails createOrderDetailsDtoToOrderDetails(CreateOrderDetailsDto createOrderDetailsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderDetails updateOrderDetailsDtoToOrderDetails(
            UpdateOrderDetailsDto updateOrderDetailsDto,@MappingTarget OrderDetails OrderDetails);

    List<OrderDetailsDto> OrderDetailsListToOrderDetailsDtoList(List<OrderDetails> storeList);
}
