package net.jkcodebase.springboot.publisher;

import net.jkcodebase.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;


    private RabbitTemplate rabbitTemplate;

    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){

        LOGGER.info(String.format("Json Message Sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
