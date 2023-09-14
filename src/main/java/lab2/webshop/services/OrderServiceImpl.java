package lab2.webshop.services;

import lab2.webshop.openapi.model.Order;
import lab2.webshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

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
