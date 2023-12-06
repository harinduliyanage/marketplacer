package lk.slt.marketplacer.service.impl;


import lk.slt.marketplacer.exceptions.OrderDetailsNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.OrderDetailsRepository;
import lk.slt.marketplacer.service.OrderDetailsService;
import lk.slt.marketplacer.service.OrderService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    OrderService orderService;

    @Override
    public OrderDetails createOrderDetail(String userId, String orderId, OrderDetails orderDetails) {
        Order foundOrder = orderService.getOrder(userId, orderId);
        List<OrderDetails> orderDetailsList = foundOrder.getOrderDetails();
        // Copy current order list
        List<OrderDetails> oldOrderDetailsList = orderDetailsList.stream().toList();
        // Add given order detail object to order detail list
        orderDetailsList.add(orderDetails);
        // Update order with new order details list
        List<OrderDetails> savedOrderDetailsList = orderService.updateOrder(orderId, foundOrder).getOrderDetails();
        savedOrderDetailsList.removeAll(oldOrderDetailsList);
        // Get created order detail
        OrderDetails savedOrderDetail = savedOrderDetailsList.get(0);
        log.info("OrderDetails has been successfully created {}", savedOrderDetail);
        return savedOrderDetail;
    }

    @Override
    public List<OrderDetails> getOrderDetails(String userId, String orderId) {
        Order order = orderService.getOrder(userId, orderId);
        return order.getOrderDetails();
    }

    @Override
    public OrderDetails getOrderDetail(String userId, String orderId, String orderDetailId) {
        List<OrderDetails> orderDetailsList = getOrderDetails(userId, orderId);
        //
        Optional<OrderDetails> found = orderDetailsList.stream()
                .filter(orderDetail -> orderDetail.getId().equals(orderDetailId))
                .findFirst();
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new OrderDetailsNotFoundException(String.format(
                    Constants.ORDER_DETAILS_NOT_FOUND_MSG, orderDetailId, orderId)
            );
        }
    }

    @Override
    public OrderDetails updateOrderDetail(String orderDetailId, OrderDetails orderDetails) {
        orderDetails.setId(orderDetailId);
        //
        OrderDetails updatedOrderDetail = orderDetailsRepository.save(orderDetails);
        log.info("OrderDetail has been successfully updated {}", updatedOrderDetail);
        return updatedOrderDetail;
    }

    @Override
    public OrderDetails removeOrderDetail(String userId, String orderId, String orderDetailId) {
        Order order = orderService.getOrder(userId, orderId);
        List<OrderDetails> orderDetailsList = order.getOrderDetails();
        //
        Optional<OrderDetails> found = orderDetailsList.stream()
                .filter(orderDetail -> orderDetail.getId().equals(orderDetailId))
                .findFirst();
        //
        if (found.isPresent()) {
            OrderDetails orderDetails = found.get();
            // Remove given order detail object from order detail list
            orderDetailsList.remove(orderDetails);
            // Update order with new order details list
            orderService.updateOrder(orderId, order);
            log.info("OrderDetails has been successfully deleted {}", orderDetails);
            return orderDetails;
        } else {
            throw new OrderDetailsNotFoundException(String.format(
                    Constants.ORDER_DETAILS_NOT_FOUND_MSG, orderDetailId, orderId)
            );
        }
    }
}
