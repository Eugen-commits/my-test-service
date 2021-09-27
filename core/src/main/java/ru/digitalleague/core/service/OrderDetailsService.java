package ru.digitalleague.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.mapper.OrderDetailsMapper;
import ru.digitalleague.core.model.OrderDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {
    private OrderDetailsMapper mapper;
    private RabbitTemplate template;

    @Autowired
    public OrderDetailsService(OrderDetailsMapper mapper, RabbitTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }

    public void createOrder(OrderDetails orderDetails){
        mapper.createOrder(orderDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String orderInLine;
        try {
            orderInLine = objectMapper.writeValueAsString(orderDetails);
            template.convertAndSend("order",orderInLine);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<OrderDetails> getAllOrders(){
        List<OrderDetails> allOrders = new ArrayList<>();
        mapper.getAllOrders().iterator().forEachRemaining(allOrders::add);
        return allOrders;
    }
}
