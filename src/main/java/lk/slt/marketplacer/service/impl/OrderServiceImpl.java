package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.DiscountInvalidException;
import lk.slt.marketplacer.exceptions.OrderNullAttributeException;
import lk.slt.marketplacer.exceptions.OrderNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.OrderRepository;
import lk.slt.marketplacer.service.CartService;
import lk.slt.marketplacer.service.OrderService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.Constants;
import lk.slt.marketplacer.util.DiscountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createOrder(String userId, String cartId, Order order) {
        List<OrderDetails> orderDetailsList;
        //
        if (userId != null && cartId != null) {
            Cart foundCart = cartService.getCart(userId, cartId);
            //
            order.setUser(foundCart.getUser());
            orderDetailsList = foundCart.getCartItems().stream().map(cartItems -> {
                Product product = cartItems.getProduct();
                double discount = product.getDiscountAmount();
                double price = calPrice(product.getPrice(), discount, product.getDiscountType());
                OrderDetails orderDetails = new OrderDetails();
                //
                orderDetails.setProduct(cartItems.getProduct());
                orderDetails.setDiscount(discount);
                orderDetails.setProduct(product);
                orderDetails.setPrice(price);
                orderDetails.setUnits(cartItems.getUnits());
                return orderDetails;
            }).toList();
        } else if (order.getOrderDetails() != null && !order.getOrderDetails().isEmpty()) {
            orderDetailsList = order.getOrderDetails();
            //
            for (OrderDetails orderDetails : orderDetailsList) {
                Product product = orderDetails.getProduct();
                double discount = product.getDiscountAmount();
                double price = calPrice(product.getPrice(), discount, product.getDiscountType());
                //
                orderDetails.setPrice(price);
                orderDetails.setDiscount(discount);
            }
        } else {
            throw new OrderNullAttributeException(Constants.ORDER_NULL_ATTRIBUTE_MSG);
        }
        //
        order.setOrderDetails(orderDetailsList);
        Order savedOrder = orderRepository.save(order);
        log.info("Order has been successfully created {}", savedOrder);
        return savedOrder;
    }

    @Override
    public Page<Order> getOrders(String userId, Pageable pageable) {
        User user = userService.getUser(userId);
        QOrder qOrder = QOrder.order;
        BooleanExpression expression = qOrder.user.eq(user);
        return orderRepository.findAll(expression, pageable);
    }

    @Override
    public Order getOrder(String userId, String orderId) {
        User user = userService.getUser(userId);
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

    private double calPrice(double price, double discount, DiscountType discountType) {
        if (discountType == DiscountType.FLAT) {
            price -= discount;
        }
        if (discountType == DiscountType.PERCENTAGE) {
            price -= price * discount / 100;
        }
        if (price < 0) {
            throw new DiscountInvalidException(Constants.INVALID_DISCOUNT_MSG);
        }
        return price;
    }
}
