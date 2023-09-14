package lab2.webshop.controllers;

import lab2.webshop.openapi.api.OrdersApi;
import lab2.webshop.openapi.model.Order;
import lab2.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController implements OrdersApi {

    /** Service bean for all orders related */
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<Order> getOrder(String orderId){
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Order> createOrder(String shoppingCartId){
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(null);
    }
}
