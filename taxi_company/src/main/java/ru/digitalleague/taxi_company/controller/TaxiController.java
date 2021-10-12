package ru.digitalleague.taxi_company.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;

import java.time.OffsetDateTime;

@RestController
public class TaxiController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderServiceImpl service;

    @PostMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestBody String message) {
        System.out.println("Trip is finished");

        amqpTemplate.convertAndSend("trip-result", message);

        return ResponseEntity.ok("Услуга оказана");
    }

    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip(@RequestBody OrderDetails orderDetails){
        orderDetails.setStartTrip(OffsetDateTime.now());
        service.setOrderStartTrip(orderDetails);
        return new ResponseEntity<>("Поездка началась в : " + orderDetails.getStartTrip(), HttpStatus.OK);
    }

    @PostMapping("/end-trip")
    public ResponseEntity<String> endTrip(@RequestBody OrderDetails orderDetails){
        orderDetails.setEndTrip(OffsetDateTime.now());
        service.setOrderEndTrip(orderDetails);
        String result = "Поездка закончилась в : " + orderDetails.getEndTrip();
        amqpTemplate.convertAndSend("trip-result", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
