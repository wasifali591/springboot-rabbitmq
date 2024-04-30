package com.example.springbootrabbitmq.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    //spring bean for rabitMq queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //spring bean for rabitMq queue with json value
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    //spring bean for rabitMq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //spring bean for binding queue to exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    //spring bean for binding json queue to exchange using routing key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    //message converter
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //RabbitTemplate for json message
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
