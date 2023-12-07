package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    public Order createOrder(String userId, String cartId, Order order);

    public Page<Order> getOrders(String userId, Pageable pageable);

    public Order getOrder(String userId, String orderId);

    public Order updateOrder(String orderId, Order order);

    public Order removeOrder(String userId, String orderId);

}
