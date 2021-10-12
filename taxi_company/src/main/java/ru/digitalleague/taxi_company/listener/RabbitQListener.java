package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;

import java.io.IOException;
import java.util.logging.Logger;


@Component
@EnableRabbit
@ConditionalOnBean(value = RabbitTemplate.class)

public class RabbitQListener {

    private Logger logger = Logger.getGlobal();

    @Autowired
    private OrderServiceImpl service;


    @RabbitListener(queues = "moscow_queue")
    public void orderListener(String message){
        logger.info("Поездка " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OrderDetails orderDetails = null;
        try {
            orderDetails = objectMapper.readValue(message, OrderDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.saveOrder(orderDetails);
    }
}
