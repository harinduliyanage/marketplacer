package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.OrderDetails;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailsService {
    public OrderDetails createOrderDetail(String userId, String orderId, OrderDetails orderDetails);

    public List<OrderDetails> getOrderDetails(String userId, String orderId);

    public OrderDetails getOrderDetail(String userId, String orderId, String orderDetailId);

    public OrderDetails updateOrderDetail(String orderDetailId, OrderDetails orderDetails);

    public OrderDetails removeOrderDetail(String userId, String orderId, String orderDetailId);
}
