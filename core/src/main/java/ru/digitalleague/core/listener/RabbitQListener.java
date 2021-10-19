package ru.digitalleague.core.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;


@Component
@EnableRabbit
@ConditionalOnBean(value = RabbitTemplate.class)
public class RabbitQListener {

    private Logger logger = Logger.getGlobal();

    @RabbitListener(queues = "trip-result")
    public void orderListener(String message){
        logger.info("Поездка завершена " + message);
    }

}
