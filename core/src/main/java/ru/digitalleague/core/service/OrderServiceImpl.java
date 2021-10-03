package ru.digitalleague.core.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.mapper.OrderDetailsMapper;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.repository.OrderDetailsService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class OrderServiceImpl implements OrderDetailsService {
    private OrderDetailsMapper mapper;
    private RabbitTemplate template;

    @Autowired
    public OrderServiceImpl(OrderDetailsMapper mapper, RabbitTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }

    @Override
    public void createOrder(OrderDetails orderDetails){
        mapper.createOrder(orderDetails);
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
