package ru.digitalleague.taxi_company.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.digitalleague.taxi_company.listener.OrderListener;

@Configuration
public class RabbitMqConfig {
    @Value("${application.broker.receive-queue}")
    private String queueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin ampqAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * Очередь для приема результатов поездки.
     */
    @Bean
    public Queue myQueue2() {
        return new Queue("trip-result");
    }

    /**
     * Очередь для приема результатов поездки.
     */
    @Bean
    public Queue myQueue3() {
        return new Queue(queueName);
    }


   /* @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        // устанавливаем очередь, которую будет слушать приложение
        simpleMessageListenerContainer.setQueues(myQueue3());
        simpleMessageListenerContainer.setMessageListener(new OrderListener());
        return simpleMessageListenerContainer;
    }*/
}
