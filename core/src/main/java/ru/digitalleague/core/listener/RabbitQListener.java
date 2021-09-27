package ru.digitalleague.core.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@EnableRabbit
public class RabbitQListener {
    private Logger logger = Logger.getGlobal();
    @RabbitListener(queues = "order")
    public void orderListener(String order){
        logger.info("order - " + order);
    }
}
