package com.example.springbootrabbitmq.publishers;

import com.example.springbootrabbitmq.dtos.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //send a message to exchange using routing key
    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("JSON message sent: " + user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }
}
