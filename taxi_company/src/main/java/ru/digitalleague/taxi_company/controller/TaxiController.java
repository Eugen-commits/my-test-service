package ru.digitalleague.taxi_company.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderResponse;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;

import java.io.Serializable;
import java.time.OffsetDateTime;

@RestController
public class TaxiController{
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderServiceImpl service;

    @ApiOperation("Контроллер подтверждения поездки")
    @PostMapping("/accept-trip")
    public ResponseEntity<String> acceptTrip(@RequestBody OrderDetails orderDetails) {
        OrderResponse driver = service.getDriverByOrderId(orderDetails);
        service.setDriverStatusTrue(orderDetails);
        return ResponseEntity.ok("Ожидайте аодителя " + driver);
    }

    @ApiOperation("Контроллер начала поездки")
    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip(@RequestBody OrderDetails orderDetails){
        orderDetails.setStartTrip(OffsetDateTime.now());
        service.setOrderStartTrip(orderDetails);
        return new ResponseEntity<>("Поездка началась в : " + orderDetails.getStartTrip(), HttpStatus.OK);
    }

    @ApiOperation("Контроллер окончания поездки")
    @PostMapping("/end-trip")
    public ResponseEntity<String> endTrip(@RequestBody OrderDetails orderDetails){
        orderDetails.setEndTrip(OffsetDateTime.now());
        service.setOrderEndTrip(orderDetails);
        String message = service.getMessage(orderDetails);
        long result = service.calculateTripCost(orderDetails);
        amqpTemplate.convertAndSend("trip-result", message);
        return new ResponseEntity<>("Поездка завершена,стоимость поездки - " + result + " рублей" +
                                          ", пожалуста оцените поездку",HttpStatus.OK);
    }

    @ApiOperation("Контроллер оценки водителя")
    @PostMapping("/grade-trip/{grade}")
    public ResponseEntity<String> gradeTrip(@PathVariable (value = "grade") Integer grade,
                                            @RequestBody OrderDetails orderDetails){
        service.setGrade(orderDetails, grade);
        return new ResponseEntity<>("Спасибо",HttpStatus.OK);
    }
}
