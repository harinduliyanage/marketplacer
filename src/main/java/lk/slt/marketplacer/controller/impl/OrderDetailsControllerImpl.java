package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.OrderDetailsController;
import lk.slt.marketplacer.dto.CreateOrderDetailsDto;
import lk.slt.marketplacer.dto.OrderDetailsDto;
import lk.slt.marketplacer.dto.UpdateOrderDetailsDto;
import lk.slt.marketplacer.dto.mapper.OrderDetailsMapper;
import lk.slt.marketplacer.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailsControllerImpl implements OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;
    @Autowired
    OrderDetailsMapper orderDetailsMapper;

    @Override
    public OrderDetailsDto createOrder(String userId, String orderId, CreateOrderDetailsDto createOrderDetailsDto) {
        return orderDetailsMapper.orderDetailsToOrderDetailsDto(
                orderDetailsService.createOrderDetail(userId, orderId,
                        orderDetailsMapper.createOrderDetailsDtoToOrderDetails(createOrderDetailsDto)
                )
        );
    }

    @Override
    public List<OrderDetailsDto> getOrderDetails(String userId, String orderId) {
        return orderDetailsMapper.orderDetailsListToOrderDetailsDtoList(
                orderDetailsService.getOrderDetails(userId, orderId)
        );
    }

    @Override
    public OrderDetailsDto getOrderDetail(String userId, String orderId, String orderDetailsId) {
        return orderDetailsMapper.orderDetailsToOrderDetailsDto(
                orderDetailsService.getOrderDetail(userId, orderId, orderDetailsId)
        );
    }

    @Override
    public OrderDetailsDto updateOrder(String userId, String orderId, String orderDetailsId, UpdateOrderDetailsDto updateOrderDetailsDto) {
        return orderDetailsMapper.orderDetailsToOrderDetailsDto(
                orderDetailsService.updateOrderDetail(orderDetailsId,
                        orderDetailsMapper.updateOrderDetailsDtoToOrderDetails(updateOrderDetailsDto,
                                orderDetailsService.getOrderDetail(userId, orderId, orderDetailsId)
                        )
                )
        );
    }

    @Override
    public OrderDetailsDto removeOrderDetail(String userId, String orderId, String orderDetailsId) {
        return orderDetailsMapper.orderDetailsToOrderDetailsDto(
                orderDetailsService.removeOrderDetail(userId, orderId, orderDetailsId)
        );
    }
}
