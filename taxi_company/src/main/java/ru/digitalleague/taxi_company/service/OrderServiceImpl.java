package ru.digitalleague.taxi_company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.DriverRatingModel;
import ru.digitalleague.core.model.OrderResponse;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.entity.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriverInfoMapper;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService , Serializable {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TaxiDriverInfoMapper driverInfoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveOrder(OrderDetails orderDetails){
        String city = orderDetails.getCity();
        orderDetails.setDriverId(driverInfoMapper.getDriverByGlobalRatingAndBusyStatus(city));
        orderMapper.saveOrder(orderDetails);
    }

    public void setOrderStartTrip (OrderDetails orderDetails){
        orderMapper.setOrderStartTripById(orderDetails);
    }

    public void setOrderEndTrip (OrderDetails orderDetails){
        orderMapper.updateOrderEndById(orderDetails);
        driverInfoMapper.setDriverStatusFalse(orderMapper.getDriverId(orderDetails));
    }

    public OrderResponse getDriverByOrderId(OrderDetails orderDetails){
        TaxiDriverInfoModel driver = orderMapper.getDriverByOrderId(orderDetails);
        System.out.println(driver);
        OrderResponse response = new OrderResponse();
        response.setFirstName(driver.getFirstName());
        response.setLastName(driver.getLastName());
        response.setCarModel(driver.getCarModel());
        return response;
    }

    public void setDriverStatusTrue(OrderDetails orderDetails){
        driverInfoMapper.setDriverStatusTrue(orderMapper.getDriverId(orderDetails));
    }

    public Long getDriverId(OrderDetails orderDetails){
        return orderMapper.getDriverId(orderDetails);
    }
    //Оценка водителя
    public void setGrade(OrderDetails orderDetails, Integer grade){
        DriverRatingModel ratingModel = new DriverRatingModel();
        ratingModel.setDriverId(getDriverId(orderDetails));
        ratingModel.setOrderId(orderDetails.getOrderId());
        ratingModel.setGrade(grade);
        System.out.println(ratingModel);
        driverInfoMapper.saveGrade(ratingModel);
        saveMiddleGrade(ratingModel.getDriverId());
    }
    //Сохранить водителю среднюю оценку по всем поездкам
    private void saveMiddleGrade(Long driverId){
        List<Integer> grade = driverInfoMapper.getAllDriverGradeByDriverId(driverId);
        System.out.println(grade);
        long sum = 0;
        for (int i = 0; i < grade.size(); i++) {
            sum = sum + grade.get(i);
        }
        int result = (int)sum/grade.size();
        driverInfoMapper.saveDriverRating(result, driverId);
    }
    //Расчет стоимости поездки, сохранение стоимости в таблицу orders
    public long calculateTripCost(OrderDetails orderDetails){
        OffsetDateTime startTrip = orderMapper.getStartTrip(orderDetails);
        OffsetDateTime endTrip = orderMapper.getEndTrip(orderDetails);
        Long driverId = getDriverId(orderDetails);
        Long orderId = orderDetails.getOrderId();
        long result = startTrip.until(endTrip, ChronoUnit.MINUTES) * driverInfoMapper.getMinuteCost(driverId);
        orderMapper.saveCost(result , orderId);
        System.out.println(result);
        return result;
    }
    //Получить заказ по id
    public OrderDetails getOrderById(OrderDetails orderDetails){
        return orderMapper.getOrderById(orderDetails);
    }


    public String getMessage (OrderDetails orderDetails){
        String message = null;
        try {
            message = objectMapper.writeValueAsString(getOrderById(orderDetails));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(message);
        return message;
    }
}
