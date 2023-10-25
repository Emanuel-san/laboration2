package lab2.webshop.internal.api.services;

import lab2.webshop.openapi.model.Order;

import java.util.List;

/**
 * Service interface for orders
 * TODO not implemented
 */

public interface OrderService {

    Order getOrder(String orderId);

    Order createOrder(String shoppingCartId);

    List<Order> getOrders();
}
