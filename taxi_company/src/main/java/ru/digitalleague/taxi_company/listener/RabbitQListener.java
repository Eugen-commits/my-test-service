package ru.digitalleague.taxi_company.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import ru.digitalleague.taxi_company.config.RabbitMqConfig;

import java.util.logging.Logger;


@Component
@EnableRabbit
@ConditionalOnBean(value = RabbitTemplate.class)

public class RabbitQListener {

    private Logger logger = Logger.getGlobal();
    @RabbitListener(queues = "moscow_queue")
    public void orderListener(String message){
        logger.info("Поездка " + message);
    }
}
