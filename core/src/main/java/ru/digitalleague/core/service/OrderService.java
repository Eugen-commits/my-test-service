package ru.digitalleague.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.mapper.OrderDetailsMapper;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.repository.OrderDetailsRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class OrderService implements OrderDetailsRepo {
    private OrderDetailsMapper mapper;
    private RabbitTemplate template;

    @Autowired
    public OrderService(OrderDetailsMapper mapper, RabbitTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }

    @Override
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
    @Override
    public List<OrderDetails> getAllOrders(){
        List<OrderDetails> allOrders = new ArrayList<>();
        mapper.getAllOrders().iterator().forEachRemaining(allOrders::add);
        return allOrders;
    }
    @Override
    public void updateOrder(OrderDetails orderDetails){
        mapper.updateByClientNumber(orderDetails);
    }
    @Override
    public void delete(OrderDetails orderDetails){
        mapper.delete(orderDetails);
    }
}
