package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.service.OrderDetailsService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderDetailsController {
    private OrderDetailsService order;

    @Autowired
    public OrderDetailsController(OrderDetailsService order) {
        this.order = order;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createOrder (@RequestBody OrderDetails orderDetails){
        order.createOrder(orderDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrders(){
        return new ResponseEntity<>(order.getAllOrders(),HttpStatus.OK);
    }
}
