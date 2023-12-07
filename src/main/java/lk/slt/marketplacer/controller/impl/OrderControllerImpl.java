package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.OrderController;
import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.dto.mapper.OrderMapper;
import lk.slt.marketplacer.model.Order;
import lk.slt.marketplacer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = orderMapper.createOrderDtoToOrder(createOrderDto);
        Order savedOrder = orderService.createOrder(createOrderDto.getUserId(), createOrderDto.getCartId(), order);
        return orderMapper.orderToOrderDto(savedOrder);
    }

    @Override
    public ListResponseDto<OrderDto> getOrders(String userId, Pageable pageable) {
        Page<Order> page = orderService.getOrders(userId, pageable);
        return ListResponseDto.<OrderDto>builder()
                .data(orderMapper.orderListToOrderDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public OrderDto getOrder(String userId, String orderId) {
        return orderMapper.orderToOrderDto(orderService.getOrder(userId, orderId));
    }

    @Override
    public OrderDto updateOrder(String userId, String orderId, UpdateOrderDto updateOrderDto) {
        return orderMapper.orderToOrderDto(orderService
                .updateOrder(orderId,
                        orderMapper.updateOrderDtoToOrder(updateOrderDto,
                                orderService.getOrder(userId, orderId)
                        )
                )
        );
    }

    @Override
    public OrderDto removeOrder(String userId, String orderId) {
        return orderMapper.orderToOrderDto(orderService.removeOrder(userId, orderId));
    }
}
