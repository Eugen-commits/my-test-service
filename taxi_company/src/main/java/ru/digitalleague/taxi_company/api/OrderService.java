package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.entity.OrderDetails;

public interface OrderService {
     void saveOrder(OrderDetails orderDetails);
     void setOrderStartTrip (OrderDetails orderDetails);
     void setOrderEndTrip (OrderDetails orderDetails);
}
