package com.example.springbootrabbitmq.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    //spring bean for rabitMq queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
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
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
