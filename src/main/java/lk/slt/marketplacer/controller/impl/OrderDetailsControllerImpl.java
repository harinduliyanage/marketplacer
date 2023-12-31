package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.OrderDetailsController;
import lk.slt.marketplacer.dto.CreateOrderDetailsDto;
import lk.slt.marketplacer.dto.OrderDetailsDto;
import lk.slt.marketplacer.dto.UpdateOrderDetailsDto;
import lk.slt.marketplacer.dto.mapper.OrderDetailsMapper;
import lk.slt.marketplacer.model.OrderDetails;
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
    public List<OrderDetailsDto> getOrderDetails(String userId, String orderId) {
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetails(userId, orderId);
        return orderDetailsMapper.orderDetailsListToOrderDetailsDtoList(
                orderDetails
        );
    }

    @Override
    public OrderDetailsDto getOrderDetail(String userId, String orderId, String orderDetailsId) {
        OrderDetails orderDetail = orderDetailsService.getOrderDetail(userId, orderId, orderDetailsId);
        return orderDetailsMapper.orderDetailsToOrderDetailsDto(
                orderDetail
        );
    }
}
