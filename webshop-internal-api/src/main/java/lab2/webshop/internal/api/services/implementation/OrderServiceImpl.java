package lab2.webshop.internal.api.services.implementation;

import lab2.webshop.internal.api.repositories.OrderRepository;
import lab2.webshop.internal.api.services.OrderService;
import lab2.webshop.openapi.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(String orderId) {
        return null;
    }

    @Override
    public Order createOrder(String shoppingCartId) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }
}
