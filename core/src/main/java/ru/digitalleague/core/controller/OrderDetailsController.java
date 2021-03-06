package ru.digitalleague.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.service.OrderServiceImpl;
import ru.digitalleague.core.service.TaxiServiceImpl;

import java.util.List;

@RestController
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
    @ApiOperation(value = "Контроллер для заказа такси")
    public ResponseEntity<String> createOrder (@RequestBody OrderDetails orderDetails){
        taxiService.notifyTaxi(orderDetails);
        return new ResponseEntity<>("Заказ принят " + orderDetails,HttpStatus.CREATED);
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
