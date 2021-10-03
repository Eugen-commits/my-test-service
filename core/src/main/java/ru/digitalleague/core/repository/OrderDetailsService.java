package ru.digitalleague.core.repository;

import ru.digitalleague.core.model.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    void createOrder(OrderDetails orderDetails);

    List<OrderDetails> getAllOrders();

    void updateOrder(OrderDetails orderDetails);

    void delete(OrderDetails orderDetails);
}
