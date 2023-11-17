package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.OrderNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.OrderRepository;
import lk.slt.marketplacer.service.OrderService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createOrder(String userId, Order order) {
        User foundUser = userService.getUserById(userId);
        //
        order.setUser(foundUser);
        Order savedOrder = orderRepository.save(order);
        log.info("Order has been successfully created {}", savedOrder);
        return savedOrder;
    }

    @Override
    public Page<Order> getOrders(String userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        QOrder qOrder = QOrder.order;
        BooleanExpression expression = qOrder.user.eq(user);
        return orderRepository.findAll(expression, pageable);
    }

    @Override
    public Order getOrder(String userId, String orderId) {
        User user = userService.getUserById(userId);
        //
        QOrder qOrder = QOrder.order;
        BooleanExpression expression = qOrder.user.eq(user).and(qOrder.id.eq(orderId));
        Optional<Order> found = orderRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new OrderNotFoundException(String.format(Constants.ORDER_NOT_FOUND_MSG, orderId, userId));
        }
    }

    @Override
    public Order updateOrder(String orderId, Order order) {
        order.setId(orderId);

        Order updatedOrder = orderRepository.save(order);
        log.info("Order has been successfully updated {}", updatedOrder);
        return updatedOrder;
    }

    @Override
    public Order removeOrder(String userId, String orderId) {
        Order order = getOrder(userId, orderId);
        //
        orderRepository.deleteById(orderId);
        log.info("Order has been successfully deleted {}", order);
        return order;
    }
}
