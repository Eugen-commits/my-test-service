package ru.digitalleague.taxi_company.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxiController {
    @Autowired
    private AmqpTemplate amqpTemplate;


    @PostMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestBody String message) {
        System.out.println("Trip is finished");

        amqpTemplate.convertAndSend("trip-result", message);

        return ResponseEntity.ok("Услуга оказана");
    }
}
