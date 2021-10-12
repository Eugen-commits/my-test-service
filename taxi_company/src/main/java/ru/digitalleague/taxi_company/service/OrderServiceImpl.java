package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriverInfoMapper;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TaxiDriverInfoMapper driverInfoMapper;

    public void saveOrder(OrderDetails orderDetails){
        orderDetails.setDriverId(findDriverByGlobalRatingAndBusyStatus());
        orderMapper.saveOrder(orderDetails);
    }

    private Long findDriverByGlobalRatingAndBusyStatus(){
        return driverInfoMapper.getDriverByGlobalRatingAndBusyStatus();
    }

    public void setOrderStartTrip (OrderDetails orderDetails){
        orderMapper.setOrderStartTripById(orderDetails);
        driverInfoMapper.setDriverStatusTrue(orderMapper.getDriverId(orderDetails));

    }

    public void setOrderEndTrip (OrderDetails orderDetails){
        orderMapper.updateOrderEndById(orderDetails);
        driverInfoMapper.setDriverStatusFalse(orderMapper.getDriverId(orderDetails));
    }

}
