package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.service.OrderServiceImpl;
import ru.digitalleague.core.service.TaxiServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderDetailsController {
    private OrderServiceImpl order;
    private TaxiServiceImpl taxiService;

    @Autowired
    public OrderDetailsController(OrderServiceImpl order, TaxiServiceImpl taxiService) {
        this.order = order;
        this.taxiService = taxiService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createOrder (@RequestBody OrderDetails orderDetails){
        order.createOrder(orderDetails);
        taxiService.notifyTaxi(orderDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrders(){
        return new ResponseEntity<>(order.getAllOrders(),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateOrder (@RequestBody OrderDetails orderDetails){
        order.updateOrder(orderDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete (@RequestBody OrderDetails orderDetails){
        order.delete(orderDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
