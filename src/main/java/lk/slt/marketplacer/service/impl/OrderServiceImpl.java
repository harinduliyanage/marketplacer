package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.*;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.OrderRepository;
import lk.slt.marketplacer.service.*;
import lk.slt.marketplacer.util.Constants;
import lk.slt.marketplacer.util.DiscountType;
import lk.slt.marketplacer.util.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressService addressService;

    @Override
    public Order createOrder(String userId, String cartId, String shippingAddressId, String billingAddressId, Order order) {
        List<OrderDetails> orderDetailsList;
        // Logged user
        if (userId != null && cartId != null) {
            if (null == billingAddressId || null == shippingAddressId) {
                throw new OrderNullAttributeException(String.format(Constants.ORDER_ADDRESS_REQUIRED_MSG, "logged user", "shippingAddressId", "billingAddressId"));
            }
            User foundUser = userService.getUser(userId);
            Cart cart = foundUser.getCart();
            //
            if (!cart.getId().equals(cartId)) {
                throw new CartForbiddenException(String.format(Constants.CART_FORBIDDEN_MSG, cartId));
            }
            if (cart.getCartItems().isEmpty()) {
                throw new CartEmptyException(Constants.CART_EMPTY_MSG);
            }
            // Get shipping and billing addresses
            Address shippingAddress = addressService.getAddressByUserIdAndId(userId, shippingAddressId);
            Address billingAddress = addressService.getAddressByUserIdAndId(userId, billingAddressId);
            //
            orderDetailsList = cart.getCartItems().stream().map(cartItems -> {
                Product product = cartItems.getProduct();
                String productId = product.getId();
                Product foundProduct = productService.getProductById(productId);
                if (foundProduct.getUnits() < cartItems.getUnits()) {
                    throw new OrderUnitsException(String.format(Constants.ORDER_UNITS_OUT_OF_RANGE_MSG, productId, cartItems.getUnits()));
                }
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
            //
            order.setUser(foundUser);
            order.setShippingAddress(shippingAddress);
            order.setBillingAddress(billingAddress);
            // Clear cart items
            cart.setCartItems(new ArrayList<>());
            cartService.updateCart(userId, cartId, cart);
            // Guest user
        } else if (order.getOrderDetails() != null && !order.getOrderDetails().isEmpty()) {
            if (null == order.getBillingAddress() || null == order.getShippingAddress()) {
                throw new OrderNullAttributeException(String.format(Constants.ORDER_ADDRESS_REQUIRED_MSG, "guest user", "shippingAddress", "billingAddress"));
            }
            // Save shipping and billing addresses
            addressService.createAddress(order.getBillingAddress());
            addressService.createAddress(order.getShippingAddress());
            orderDetailsList = order.getOrderDetails();
            //
            for (OrderDetails orderDetails : orderDetailsList) {
                Product product = orderDetails.getProduct();
                String productId = product.getId();
                Product foundProduct = productService.getProductById(productId);
                if (foundProduct.getUnits() < orderDetails.getUnits()) {
                    throw new OrderUnitsException(String.format(Constants.ORDER_UNITS_OUT_OF_RANGE_MSG, productId, orderDetails.getUnits()));
                }
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
        order.setOrderStatus(OrderStatus.PENDING);
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
            price -= price * discount * 0.01;
        }
        if (price < 0) {
            throw new DiscountInvalidException(Constants.INVALID_DISCOUNT_MSG);
        }
        return price;
    }
}