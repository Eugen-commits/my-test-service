package ru.digitalleague.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.api.TaxiService;
import ru.digitalleague.core.mapper.TaxiInfoMapper;
import ru.digitalleague.core.model.OrderDetails;

@Service
public class TaxiServiceImpl implements TaxiService {
    private ObjectMapper objectMapper;

    private AmqpTemplate amqpTemplate;

    private TaxiInfoMapper mapper;

    @Autowired
    public TaxiServiceImpl(ObjectMapper objectMapper, AmqpTemplate amqpTemplate, TaxiInfoMapper mapper) {
        this.objectMapper = objectMapper;
        this.amqpTemplate = amqpTemplate;
        this.mapper = mapper;
    }

    @Override
    public String notifyTaxi(OrderDetails orderDetails) {

        String orderInLine = null;
        try {
            orderInLine = objectMapper.writeValueAsString(orderDetails);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String queueByCity = mapper.getQueueByCity(orderDetails.getCity());
        amqpTemplate.convertAndSend(queueByCity, orderInLine);
        return null;
    }
}
